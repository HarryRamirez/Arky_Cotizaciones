package com.arky.cotizaciones.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arky.cotizaciones.dto.DiscountResponseDTO;

@Service
public interface DiscountService {

List<DiscountResponseDTO> getAll();
}
