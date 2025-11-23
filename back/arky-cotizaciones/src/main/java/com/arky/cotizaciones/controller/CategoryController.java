package com.arky.cotizaciones.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arky.cotizaciones.dto.CategoryRequestDTO;
import com.arky.cotizaciones.dto.CategoryResponseDTO;
import com.arky.cotizaciones.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;


	// Listar todas las categorias
	@GetMapping("/all")
	public ResponseEntity<List<CategoryResponseDTO>> getAll(){
		return ResponseEntity.ok().body(categoryService.getAll());
	}


	// guardar categoria
	@PostMapping("/save")
	public ResponseEntity<CategoryResponseDTO> save(@RequestBody CategoryRequestDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.saveCategory(dto));
	}


	@PutMapping("update/{id}")
	public ResponseEntity<CategoryResponseDTO> update(@PathVariable("id") int categoryId, @RequestBody CategoryRequestDTO dto){
		return ResponseEntity.ok().body(categoryService.updateCategory(categoryId, dto)); 
	}


	@DeleteMapping("delete/{id}")
	public ResponseEntity<Map<String, String>> delete(@PathVariable("id") int categoryId){
		Map<String, String> result = new HashMap<>();
		categoryService.deleteCategory(categoryId);
		result.put("message", "Eliminado con exito");
		return ResponseEntity.ok().body(result);
	}
}
