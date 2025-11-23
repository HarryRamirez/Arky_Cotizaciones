package com.arky.cotizaciones.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arky.cotizaciones.dto.RoleResponseDTO;




@Service
public interface RoleService {


	List<RoleResponseDTO> getAll();

	RoleResponseDTO getByRole(Integer roleId);
}
