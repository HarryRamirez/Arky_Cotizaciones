package com.arky.cotizaciones.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arky.cotizaciones.config.exception.ResourceNotFoundException;
import com.arky.cotizaciones.dto.RequestItemDTO;
import com.arky.cotizaciones.model.Item;
import com.arky.cotizaciones.model.Product;
import com.arky.cotizaciones.repository.ItemRepository;
import com.arky.cotizaciones.repository.ProductRepository;
import com.arky.cotizaciones.repository.QuotationRepository;
import com.arky.cotizaciones.service.ItemService;



@Service
public class ItemServiceImpl implements ItemService{



	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ProductRepository  productRepository;

	@Autowired
	private QuotationRepository  quotationRepository;




	// Listar items
	@Override
	public List<Item> getAll() {

		return itemRepository.findAll();
	}



	@Override
	public Item saveItem(Item item) {

		return itemRepository.save(item);

	}



	// Guardar item
	public Item save(RequestItemDTO itemDto) {

		 quotationRepository.findById(itemDto.getQuotationId())
	                .orElseThrow(() -> new ResourceNotFoundException("Quotation not found"));

	    // Buscar el Product por el ID enviado en el JSON
	    Product product = productRepository.findById(itemDto.getProductId())
	        .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

	    // Crear el Item y asignar el Product
	    Item item = new Item();
	    item.setQuantity(itemDto.getQuantity());
	    item.setUnitValue(itemDto.getUnitValue());
	    item.setTotalValue(itemDto.getTotalValue());
	    item.setQuotationId(itemDto.getQuotationId());
	    item.setProduct(product); // Aquí se asigna el producto completo

	    // Guardar el Item
	    return itemRepository.save(item);
	}



	// Eliminar item
	@Override
	public void deleteItem(int itemId) {
		itemRepository.findById(itemId).ifPresent(item -> {
			itemRepository.delete(item);
		});

	}

}
