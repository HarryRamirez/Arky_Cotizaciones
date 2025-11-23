package com.arky.cotizaciones.service.impl;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arky.cotizaciones.config.exception.ResourceNotFoundException;
import com.arky.cotizaciones.dto.ItemDTO;
import com.arky.cotizaciones.dto.QuotationDTO;
import com.arky.cotizaciones.dto.QuotationFind;
import com.arky.cotizaciones.dto.QuotationSearchDTO;
import com.arky.cotizaciones.dto.RequestItemDTO;
import com.arky.cotizaciones.dto.RequestQuotationDTO;
import com.arky.cotizaciones.dto.UpdateItemDTO;
import com.arky.cotizaciones.dto.UpdateQuotationDTO;
import com.arky.cotizaciones.model.Customer;
import com.arky.cotizaciones.model.Discount;
import com.arky.cotizaciones.model.Item;
import com.arky.cotizaciones.model.Product;
import com.arky.cotizaciones.model.Quotation;
import com.arky.cotizaciones.model.State;
import com.arky.cotizaciones.model.User;
import com.arky.cotizaciones.repository.CustomerRepository;
import com.arky.cotizaciones.repository.DiscountRepository;
import com.arky.cotizaciones.repository.ItemRepository;
import com.arky.cotizaciones.repository.ProductRepository;
import com.arky.cotizaciones.repository.QuotationRepository;
import com.arky.cotizaciones.repository.StateRepository;
import com.arky.cotizaciones.repository.UserRepository;
import com.arky.cotizaciones.service.QuotationService;

import jakarta.transaction.Transactional;





@Service
public class QuotationServiceImpl implements QuotationService{


	@Autowired
	private QuotationRepository quotationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ItemRepository itemRepository;






    // Listar cotizaciones
	@Override
	public List<QuotationDTO> getAll() {

		 List<Quotation> quotations = quotationRepository.findAll();

		    return quotations.stream().map(quotation -> {
		        return new QuotationDTO(
		            quotation.getQuotationId(),
		            quotation.getUser().getFirstname() + " " + quotation.getUser().getLastname(),
		            quotation.getCustomer().getName(),
		            quotation.getDiscount().getPercentage(),
		            quotation.getRemitter(),
		            quotation.getDateQuotation(),
		            quotation.getDateEvent(),
		            quotation.getReference(),
		            quotation.getState(),
		            quotation.getNet(),
		            quotation.getIva(),
		            quotation.getTotal(),

		            quotation.getItems().stream().map(item -> new ItemDTO(
		                item.getProduct().getName(),
		                item.getProduct().getDescription(),
		                item.getQuantity(),
		                item.getUnitValue(),
		                item.getTotalValue()
		            )).collect(Collectors.toList())
		        );
		    }).collect(Collectors.toList());
	    }






	// Obtener cotizacion
	@Override
	public Optional<QuotationFind> getByQuotation(int quotationId) {

	    // Buscar la cotización por su ID
	    Optional<Quotation> quotationOpt = quotationRepository.findById(quotationId);

	    // Convertir la cotización encontrada a QuotationDTO si existe
	    return quotationOpt.map(quotation -> new QuotationFind(
	        quotation.getQuotationId(),
	        quotation.getUser(),
	        quotation.getCustomer(),
	        quotation.getDiscount(),
	        quotation.getRemitter(),
	        quotation.getDateQuotation(),
	        quotation.getDateEvent(),
	        quotation.getReference(),
	        quotation.getState(),
            quotation.getNet(),
            quotation.getIva(),
            quotation.getTotal(),
	        // Conversión de los ítems a ItemDTO
	        quotation.getItems().stream().map(item -> new ItemDTO(
	            item.getProduct().getName(),
	            item.getProduct().getDescription(),
	            item.getQuantity(),
	            item.getUnitValue(),
	            item.getTotalValue()
	        )).collect(Collectors.toList())
	    ));
	}




	// Guardar cotizacion
	  public void save(RequestQuotationDTO requestQuotationDTO) {

	        // Verificación de entidades relacionadas
	        User user = userRepository.findById(requestQuotationDTO.getUserId())
	                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

	        Customer customer = customerRepository.findById(requestQuotationDTO.getCustomerId())
	                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

	        Discount discount = discountRepository.findById(requestQuotationDTO.getDiscountId())
	                .orElseThrow(() -> new ResourceNotFoundException("Discount not found"));

	        State state = stateRepository.findById(2)
	                .orElseThrow(() -> new ResourceNotFoundException("State not found"));

	        // Crear la cotización
	        Quotation quotation = new Quotation();
	        quotation.setUser(user);
	        quotation.setCustomer(customer);
	        quotation.setDiscount(discount);
	        quotation.setRemitter(requestQuotationDTO.getRemitter());
	        quotation.setDateQuotation(requestQuotationDTO.getDateQuotation());
	        quotation.setDateEvent(requestQuotationDTO.getDateEvent());
	        quotation.setReference(requestQuotationDTO.getReference());
	        quotation.setState(state);
	        quotation.setNet(requestQuotationDTO.getNet());
	        quotation.setIva(requestQuotationDTO.getIva());
	        quotation.setTotal(requestQuotationDTO.getTotal());


	        // Guardar la cotización
	        Quotation savedQuotation = quotationRepository.save(quotation);

	        // Guardar los ítems asociados
	        for (RequestItemDTO itemDTO : requestQuotationDTO.getItems()) {
	            // Verificación de producto
	            Product product = productRepository.findById(itemDTO.getProductId())
	                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

	            // Crear y guardar el ítem
	            Item item = new Item();
	            item.setQuotationId(savedQuotation.getQuotationId()); // Asociar el ítem a la cotización
	            item.setProduct(product);
	            item.setQuantity(itemDTO.getQuantity());
	            item.setUnitValue(itemDTO.getUnitValue());
	            item.setTotalValue(itemDTO.getTotalValue());

	            itemRepository.save(item);  // Guardar el ítem en la base de datos
	        }

	    }






	// Eliminar cotizacion
	@Override
	public void deleteQuotation(int quotationId) {
		quotationRepository.findById(quotationId).ifPresent(quotation -> {
			quotationRepository.delete(quotation);
		});

	}




	// Actualizar cotizacion
    @Transactional
    public void updateQuotation(Integer quotationId, UpdateQuotationDTO updateQuotationDTO) {
        // Buscar la cotización existente
        Quotation existingQuotation = quotationRepository.findById(quotationId)
            .orElseThrow(() -> new RuntimeException("Quotation not found"));

        // Actualizar los campos de la cotización
        existingQuotation.setUser(userRepository.findById(updateQuotationDTO.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found")));
        existingQuotation.setCustomer(customerRepository.findById(updateQuotationDTO.getCustomerId())
            .orElseThrow(() -> new RuntimeException("Customer not found")));
        existingQuotation.setDiscount(discountRepository.findById(updateQuotationDTO.getDiscountId())
            .orElseThrow(() -> new RuntimeException("Discount not found")));
        existingQuotation.setRemitter(updateQuotationDTO.getRemitter());
        existingQuotation.setDateQuotation(updateQuotationDTO.getDateQuotation());
        existingQuotation.setDateEvent(updateQuotationDTO.getDateEvent());
        existingQuotation.setReference(updateQuotationDTO.getReference());
        existingQuotation.setNet(updateQuotationDTO.getNet());
        existingQuotation.setIva(updateQuotationDTO.getIva());
        existingQuotation.setTotal(updateQuotationDTO.getTotal());

        // Guardar los cambios en la cotización
        quotationRepository.save(existingQuotation);

        // Eliminar los ítems existentes para esta cotización
        itemRepository.deleteByQuotationId(quotationId);

        // Agregar los nuevos ítems
        for (UpdateItemDTO itemDTO : updateQuotationDTO.getItems()) {
            Item item = new Item();

            // Si estás creando un nuevo ítem, omite la asignación del ID
            // Si estás actualizando un ítem existente, asegúrate de que `itemDTO.getItemId()` no sea nulo
            if (itemDTO.getItemId() != null) {
                item.setItemId(itemDTO.getItemId());
            }


            Product product = productRepository.findById(itemDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));


            item.setProduct(product);
            item.setQuantity(itemDTO.getQuantity());
            item.setUnitValue(itemDTO.getUnitValue());
            item.setTotalValue(itemDTO.getTotalValue());
            item.setQuotationId(quotationId);

            itemRepository.save(item);
        }
    }




    // Actualizar estado de la cotizacion
    public Quotation updateState(Integer quotationId, Integer stateId) {

        Quotation quotation = quotationRepository.findById(quotationId)
                .orElseThrow(() -> new ResourceNotFoundException("Quotation not found"));

        State state = stateRepository.findById(stateId)
                .orElseThrow(() -> new ResourceNotFoundException("State not found"));

        quotation.setState(state);

        return quotationRepository.save(quotation);
    }




    // Conteo de cotizaciones segun su estado
	@Override
	public Integer countByEstadoId(int stateId) {
		return (int) quotationRepository.countByStateStateId(stateId);
	}




	// Buscar cotizacion
	@Override
	public List<QuotationSearchDTO> searchQuotation(String name) {

		List<Quotation> quotations = quotationRepository.findByCustomer_NameIgnoreCaseContaining(name);
        // Convertimos los resultados a DTOs
        return quotations.stream()
                .map(quotation -> new QuotationSearchDTO(
                        quotation.getQuotationId(),
                        quotation.getCustomer().getName(), // Obtenemos solo el nombre del cliente
                        quotation.getRemitter(),
                        quotation.getDateQuotation(),
                        quotation.getDateEvent(),
                        quotation.getNet(),
                        quotation.getTotal(),
                        quotation.getState()


                ))
                .collect(Collectors.toList());
	}

}
