package com.arky.cotizaciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arky.cotizaciones.dto.DiscountResponseDTO;
import com.arky.cotizaciones.service.DiscountService;

@RestController
@RequestMapping("discount")
public class DiscountController {

	@Autowired
	private DiscountService discountService;


	// Listar los descuentos
	@GetMapping("/all")
	public ResponseEntity<List<DiscountResponseDTO>> getAll(){
		 return ResponseEntity.ok().body(discountService.getAll());
	}
}
