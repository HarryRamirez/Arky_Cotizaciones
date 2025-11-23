package com.arky.cotizaciones.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arky.cotizaciones.dto.StateResponseDTO;


@Service
public interface StateService {

	List<StateResponseDTO> getAll();
}
