package com.arky.cotizaciones.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arky.cotizaciones.config.exception.ResourceNotFoundException;
import com.arky.cotizaciones.dto.RoleResponseDTO;
import com.arky.cotizaciones.mapper.RoleMapper;
import com.arky.cotizaciones.model.Role;
import com.arky.cotizaciones.repository.RoleRepository;
import com.arky.cotizaciones.service.RoleService;



@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;


	@Autowired
	private RoleMapper mapper;

	// Listar roles
	@Override
	public List<RoleResponseDTO> getAll() {

		return roleRepository.findAll().stream()
		.map(mapper::toDto).toList();
	}


	// Obtener rol
	@Override
	public RoleResponseDTO getByRole(Integer roleId) {
		Role role = roleRepository.findById(roleId)
		.orElseThrow(() -> new ResourceNotFoundException("No se encontro el rol"));

		return mapper.toDto(role);
		
	}


}
