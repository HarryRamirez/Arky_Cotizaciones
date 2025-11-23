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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arky.cotizaciones.dto.ProductDetailsDTO;
import com.arky.cotizaciones.dto.ProductRequestDTO;
import com.arky.cotizaciones.dto.ProductResponseDTO;
import com.arky.cotizaciones.service.ProductService;

@RestController
@RequestMapping("/service")
public class ProductController {


	@Autowired
	private ProductService productService;




	// Listar los productos
	@GetMapping("/all")
	public ResponseEntity<List<ProductResponseDTO>> getAll(){
		return ResponseEntity.ok().body(productService.getAll());
	}



	// Obtener un producto
	@GetMapping("/{id}")
	public ResponseEntity<ProductDetailsDTO> getProduct(@PathVariable("id") int productId){
		return ResponseEntity.ok().body(productService.getByProduct(productId));
	}


	// Guardar un producto
	@PostMapping("/save")
	public ResponseEntity<ProductDetailsDTO> save(@RequestBody ProductRequestDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(dto));
	}



	// Eliminar un producto
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, String>> delete(@PathVariable("id") int productId){
		Map<String, String> result = new HashMap<>();
		productService.deleteProduct(productId);
		result.put("message", "eliminado con exito");

		return ResponseEntity.ok().body(result);
	}



	// Actualizar un producto
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDetailsDTO> updateProduct(@PathVariable("id") Integer productId, @RequestBody ProductRequestDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.updateProduct(productId, dto));
    }



    // Buscar un producto
	@GetMapping("/search")
    public ResponseEntity<List<ProductResponseDTO>> searchServices(@RequestParam String name) {
		List<ProductResponseDTO> products =  productService.searchproduct(name);
		return ResponseEntity.ok().body(products);
    }






}
