package com.arky.cotizaciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arky.cotizaciones.dto.StateResponseDTO;
import com.arky.cotizaciones.service.StateService;

@RestController
@RequestMapping("/state")
public class StateController {

	@Autowired
	private StateService stateService;

	// Listar estado
	@GetMapping("/all")
	public ResponseEntity<List<StateResponseDTO>> getAll(){
		return ResponseEntity.ok().body(stateService.getAll());
	}
}
