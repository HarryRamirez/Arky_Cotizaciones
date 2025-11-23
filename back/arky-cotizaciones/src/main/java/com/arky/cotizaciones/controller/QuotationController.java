package com.arky.cotizaciones.controller;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arky.cotizaciones.dto.QuotationDTO;
import com.arky.cotizaciones.dto.QuotationFind;
import com.arky.cotizaciones.dto.QuotationSearchDTO;
import com.arky.cotizaciones.dto.RequestQuotationDTO;
import com.arky.cotizaciones.dto.UpdateQuotationDTO;
import com.arky.cotizaciones.dto.UpdateStateDTO;
import com.arky.cotizaciones.model.Quotation;
import com.arky.cotizaciones.repository.QuotationRepository;
import com.arky.cotizaciones.service.QuotationService;
import com.arky.cotizaciones.service.impl.PdfServiceImpl;
import com.arky.cotizaciones.service.impl.QuotationServiceImpl;
import com.itextpdf.text.DocumentException;

@RestController
@RequestMapping("/quotation")
public class QuotationController {



	@Autowired
	private QuotationRepository quotationRepository;

	@Autowired
	private QuotationService quotationService;

	@Autowired
	private QuotationServiceImpl quotationServiceImpl;


	@Autowired
	private PdfServiceImpl pdfServiceImpl;

    @Autowired
    public QuotationController(PdfServiceImpl pdfServiceImpl) {
        this.pdfServiceImpl = pdfServiceImpl;
    }



    // Listar las cotizaciones
	@GetMapping("/all")
	public ResponseEntity<List<QuotationDTO>> getAll(){
		List<QuotationDTO> quotations = quotationService.getAll();
		return new ResponseEntity<>(quotations, HttpStatus.OK);
	}


	// Obtener una cotizacion
	@GetMapping("/{id}")
	public ResponseEntity<QuotationFind> getQuotation(@PathVariable("id") int quotationId){
		      Optional<QuotationFind> quotationDTO =  quotationService.getByQuotation(quotationId);
			return	quotationDTO.map(quotation -> new ResponseEntity<>(quotation, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}



	// Guardar cotizacion
    @PostMapping("/save")
    public ResponseEntity<Void> createQuotation(@RequestBody RequestQuotationDTO requestQuotationDTO) {
        quotationServiceImpl.save(requestQuotationDTO);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }


    // Eliminar cotizacion
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") int quotationId){
		if(quotationRepository.findById(quotationId).isPresent()) {
			quotationService.deleteQuotation(quotationId);
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}



	// Actualizar cotizacion
	@PutMapping("/update/{id}")
	public ResponseEntity<Void> updateQuotation(
	    @PathVariable("id") Integer quotationId,
	    @RequestBody UpdateQuotationDTO updateQuotationDTO) {

	    quotationServiceImpl.updateQuotation(quotationId, updateQuotationDTO);
	    return ResponseEntity.noContent().build();
	}



	// Actulizar estado de la cotizacion
    @PutMapping("/update/state/{id}")
    public ResponseEntity<Quotation> updateState(
            @PathVariable("id") Integer quotationId,
            @RequestBody UpdateStateDTO updateStateDTO) {

        Quotation updatedQuotation = quotationServiceImpl.updateState(quotationId, updateStateDTO.getStateId());

        return ResponseEntity.ok(updatedQuotation);
    }



    // contar cotizacion segun su estado
    @GetMapping("/count")
    public Integer contarCotizacionesPorEstado(@RequestParam("stateId") int stateId) {
        return quotationService.countByEstadoId(stateId);
    }



    // Buscar cotizacion
	@GetMapping("/search")
	public ResponseEntity<List<QuotationSearchDTO>> search(@RequestParam String name){
		List<QuotationSearchDTO> quotations = quotationService.searchQuotation(name);

        if (!quotations.isEmpty()) {
            return new ResponseEntity<>(quotations, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
	}



	// Generar PDF de la cotizacion
    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable int id) {
        ByteArrayInputStream bis;
        try {
            bis = pdfServiceImpl.generatePdf(id);
        } catch (DocumentException e) {
            return ResponseEntity.status(500).body(null);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=cotizacion_" + id + ".pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(bis.readAllBytes());
    }

}
