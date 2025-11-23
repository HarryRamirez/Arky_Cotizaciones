package com.arky.cotizaciones.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arky.cotizaciones.config.exception.ResourceNotFoundException;
import com.arky.cotizaciones.dto.CategoryRequestDTO;
import com.arky.cotizaciones.dto.CategoryResponseDTO;
import com.arky.cotizaciones.mapper.CategoryMapper;
import com.arky.cotizaciones.model.Category;
import com.arky.cotizaciones.repository.CategoryRepository;
import com.arky.cotizaciones.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryMapper mapper;


	// Listar categorias
	@Transactional(readOnly = true)
	@Override
	public List<CategoryResponseDTO> getAll() {

		return categoryRepository.findAll().stream()
		.map(mapper::toDto).toList();
	}


	// Guardar categoria
	@Transactional
	@Override
	public CategoryResponseDTO saveCategory(CategoryRequestDTO dto) {
		Category category = mapper.toEntity(dto);
		return mapper.toDto(categoryRepository.save(category));
	}



	@Transactional
	@Override
	public CategoryResponseDTO updateCategory(int categoryId, CategoryRequestDTO dto) {
		Category category = categoryRepository.findById(categoryId)
		.orElseThrow(() -> new ResourceNotFoundException("No se pudo actualizar"));
		mapper.updateDtoFromCategory(dto, category);

		return mapper.toDto(categoryRepository.save(category));
	}


	@Transactional
	@Override
	public void deleteCategory(int categoryId) {
		Category category = categoryRepository.findById(categoryId)
		.orElseThrow(() -> new ResourceNotFoundException("No se pudo eliminar"));
		categoryRepository.delete(category);
	}



}
