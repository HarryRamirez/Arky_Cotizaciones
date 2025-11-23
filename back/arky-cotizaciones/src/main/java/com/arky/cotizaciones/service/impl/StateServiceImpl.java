package com.arky.cotizaciones.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arky.cotizaciones.dto.StateResponseDTO;
import com.arky.cotizaciones.mapper.StateMapper;
import com.arky.cotizaciones.repository.StateRepository;
import com.arky.cotizaciones.service.StateService;

@Service
public class StateServiceImpl implements StateService{

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private StateMapper mapper;



	// Listar estados
	@Override
	public List<StateResponseDTO> getAll() {

		return stateRepository.findAll().stream()
		.map(mapper::toDto).toList();
	}

}
