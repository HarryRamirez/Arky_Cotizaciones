package com.arky.cotizaciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arky.cotizaciones.config.exception.ResourceNotFoundException;
import com.arky.cotizaciones.dto.RequestItemDTO;
import com.arky.cotizaciones.model.Item;
import com.arky.cotizaciones.repository.ItemRepository;
import com.arky.cotizaciones.service.ItemService;
import com.arky.cotizaciones.service.impl.ItemServiceImpl;

@RestController
@RequestMapping("/item")
public class ItemController {



	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ItemServiceImpl  itemServiceImpl;

	@Autowired
	private ItemService itemService;



	// Listar los items
	@GetMapping("/all")
	public ResponseEntity<List<Item>> getAll(){
		return new ResponseEntity<>(itemService.getAll(), HttpStatus.OK);
	}


	// Guardar los items
    @PostMapping("/save")
    public ResponseEntity<Item> createItem(@RequestBody RequestItemDTO itemDto) {
        try {
            Item item = itemServiceImpl.save(itemDto);
            return new ResponseEntity<>(item, HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Eliminar un item
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") int itemId){
		if(itemRepository.findById(itemId).isPresent()) {
			itemService.deleteItem(itemId);
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
