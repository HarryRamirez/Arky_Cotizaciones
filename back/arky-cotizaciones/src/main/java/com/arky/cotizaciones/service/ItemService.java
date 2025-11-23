package com.arky.cotizaciones.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arky.cotizaciones.model.Item;



@Service
public interface ItemService {


	List<Item> getAll();

	Item saveItem(Item item);

	void  deleteItem(int itemId);


}
