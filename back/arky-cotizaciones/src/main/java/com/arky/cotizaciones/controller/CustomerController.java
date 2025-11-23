package com.arky.cotizaciones.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.arky.cotizaciones.dto.CustomerRequestDTO;
import com.arky.cotizaciones.dto.CustomerResponseDTO;
import com.arky.cotizaciones.service.CustomerService;






@RestController
@RequestMapping("/customer")
public class CustomerController {


	@Autowired
	private CustomerService customerService;



    // Listar todos los clientes
	@GetMapping("/all")
	public ResponseEntity<List<CustomerResponseDTO>> getAll(){
		return ResponseEntity.ok().body(customerService.getAll());
	}


	// Obtener un cliente
	@GetMapping("/{id}")
	public ResponseEntity<CustomerResponseDTO> getCustomer(@PathVariable("id") int customerId){
		return ResponseEntity.ok().body(customerService.getByCustomer(customerId));
	}



	// Guardar un cliente
	@PostMapping("/save")
	public ResponseEntity<CustomerResponseDTO> save(@RequestBody CustomerRequestDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(customerService.saveCustomer(dto));
	}



	// Eliminar un cliente
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, String>> delete(@PathVariable("id") int customerId) {
        Map<String, String> result = new HashMap<>();
        result.put("message", "eliminado con exito");
        customerService.deleteCustomer(customerId);

        return ResponseEntity.ok().body(result);
	}


	// Actualizar un cliente
    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerResponseDTO> update(@PathVariable("id") int customerId, @RequestBody CustomerRequestDTO dto) {
        return ResponseEntity.ok().body(customerService.updateCustomer(customerId, dto));
    }



    // Buscar un cliente
    @GetMapping("/search")
    public ResponseEntity<List<CustomerResponseDTO>> searchCustomer(@RequestParam String name) {
        List<CustomerResponseDTO> customers = customerService.searchCustomer(name);

        if (!customers.isEmpty()) {
            return new ResponseEntity<>(customers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }



    // reporte en Excel
    @GetMapping("/excel")
    public ResponseEntity<byte[]> exportCustomersToExcel() throws IOException {
        ByteArrayInputStream excelStream = customerService.exportCustomersToExcel();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=customers.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(excelStream.readAllBytes());
    }

}


