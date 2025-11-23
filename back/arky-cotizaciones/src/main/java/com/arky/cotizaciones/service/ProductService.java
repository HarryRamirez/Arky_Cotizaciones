package com.arky.cotizaciones.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arky.cotizaciones.dto.ProductDetailsDTO;
import com.arky.cotizaciones.dto.ProductRequestDTO;
import com.arky.cotizaciones.dto.ProductResponseDTO;

@Service
public interface ProductService {

	List<ProductResponseDTO> getAll();

	ProductDetailsDTO  getByProduct(int productId);

	ProductDetailsDTO saveProduct(ProductRequestDTO dto);

	ProductDetailsDTO updateProduct(int productId, ProductRequestDTO dto);

	void deleteProduct(int productId);

	List<ProductResponseDTO> searchproduct(String name);

}
