package com.arky.cotizaciones.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arky.cotizaciones.dto.DiscountResponseDTO;
import com.arky.cotizaciones.mapper.DiscountMapper;
import com.arky.cotizaciones.model.Discount;
import com.arky.cotizaciones.repository.DiscountRepository;
import com.arky.cotizaciones.service.DiscountService;

@Service
public class DiscountServiceImpl implements DiscountService{

	@Autowired
	private DiscountRepository discountRepository;

	@Autowired
	private DiscountMapper mapper;



	// Listar descuentos
	@Override
	public List<DiscountResponseDTO> getAll() {

		return discountRepository.findAll().stream()
		.map(mapper::toDto).toList();
	}

}
