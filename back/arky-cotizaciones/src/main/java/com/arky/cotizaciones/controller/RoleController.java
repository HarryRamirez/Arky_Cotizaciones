package com.arky.cotizaciones.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arky.cotizaciones.dto.RoleResponseDTO;
import com.arky.cotizaciones.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;



	// Listar roles
	@GetMapping("/all")
	public ResponseEntity<List<RoleResponseDTO>> getAll(){
		return ResponseEntity.ok().body(roleService.getAll());
	}


	// Obtener rol
	@GetMapping("/{id}")
	public ResponseEntity<RoleResponseDTO> getRole(@PathVariable("id") int roleId){
		return ResponseEntity.ok().body(roleService.getByRole(roleId));
	}

}
