package com.arky.cotizaciones.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.arky.cotizaciones.dto.CategoryRequestDTO;
import com.arky.cotizaciones.dto.CategoryResponseDTO;

@Service
public interface CategoryService {

	List<CategoryResponseDTO> getAll();

	CategoryResponseDTO saveCategory(CategoryRequestDTO dto);

	CategoryResponseDTO updateCategory(int categoryId, CategoryRequestDTO dto);

	void deleteCategory(int categoryId);
}
