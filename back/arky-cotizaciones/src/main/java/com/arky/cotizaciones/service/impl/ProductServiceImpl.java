package com.arky.cotizaciones.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arky.cotizaciones.config.exception.DuplicateResourceException;
import com.arky.cotizaciones.config.exception.ResourceNotFoundException;
import com.arky.cotizaciones.dto.ProductDetailsDTO;
import com.arky.cotizaciones.dto.ProductRequestDTO;
import com.arky.cotizaciones.dto.ProductResponseDTO;
import com.arky.cotizaciones.mapper.ProductMapper;
import com.arky.cotizaciones.model.Category;
import com.arky.cotizaciones.model.Product;
import com.arky.cotizaciones.repository.CategoryRepository;
import com.arky.cotizaciones.repository.ProductRepository;
import com.arky.cotizaciones.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{


	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;


	@Autowired
	private ProductMapper mapper;




	// Listar productos
	@Transactional(readOnly = true)
	@Override
	public List<ProductResponseDTO> getAll() {

		return productRepository.findAll().stream()
				.map(mapper::toDto).toList();
	}



	// Obtener un producto
	@Transactional(readOnly = true)
	@Override
	public ProductDetailsDTO getByProduct(int productId) {
		Product product = productRepository.findById(productId)
		.orElseThrow(() -> new ResourceNotFoundException("No se encontro el producto"));

		return mapper.toDtoDetails(product);
	}





	// Guardar un producto
	@Transactional
	@Override
	public ProductDetailsDTO saveProduct(ProductRequestDTO dto) {
		Product product = mapper.toEntity(dto);
		if(productRepository.existsByNameContainingIgnoreCase(dto.getName())){
			throw new DuplicateResourceException("Ya existe ese producto");
		}

		Category category = categoryRepository.findById(dto.getCategory())
        .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));

		product.setCategory(category);// esto para que me muestre categoria en el retorno
									// por que si guarda con categoria pero el nombre me lo da null
									// en el retorno

		return mapper.toDtoDetails(productRepository.save(product)); 
	}




	// Actualizar producto
	@Transactional
	@Override
    public ProductDetailsDTO updateProduct(int productId, ProductRequestDTO dto) {

        Product product = productRepository.findById(productId)
		.orElseThrow(() -> new ResourceNotFoundException("No se pudo actualizar el producto"));

		Category category = categoryRepository.findById(dto.getCategory())
		.orElseThrow(()  -> new ResourceNotFoundException("No se encontro la categoria"));

		product.setCategory(category);

		mapper.updateDtoFromProduct(dto, product);

		return mapper.toDtoDetails(productRepository.save(product));
 
    }




    // Eliminar producto
	@Transactional
	@Override
	public void deleteProduct(int productId) {
		Product product = productRepository.findById(productId)
		.orElseThrow(() -> new ResourceNotFoundException("No se pudo eliminar"));

		productRepository.delete(product);

	}




	// Buscar producto
	@Transactional(readOnly = true)
	@Override
	public List<ProductResponseDTO> searchproduct(String name) {
		
		return productRepository.findByNameContainingIgnoreCase(name).stream()
		.map(mapper::toDto).toList();
                
	}






}
