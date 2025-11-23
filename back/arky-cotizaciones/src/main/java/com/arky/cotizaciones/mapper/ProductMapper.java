package com.arky.cotizaciones.mapper;

import org.springframework.stereotype.Component;

import com.arky.cotizaciones.dto.CategoryResponseDTO;
import com.arky.cotizaciones.dto.ProductDetailsDTO;
import com.arky.cotizaciones.dto.ProductRequestDTO;
import com.arky.cotizaciones.dto.ProductResponseDTO;
import com.arky.cotizaciones.model.Category;
import com.arky.cotizaciones.model.Product;

@Component
public class ProductMapper {


    public ProductResponseDTO toDto(Product product){
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setProductId(product.getProductId());
        dto.setName(product.getName());
        dto.setNet(product.getNet());
        dto.setDescription(product.getDescription());
        

        return dto;
    }



    public ProductDetailsDTO toDtoDetails(Product product){
        ProductDetailsDTO dto = new ProductDetailsDTO();
        dto.setProductId(product.getProductId());
        dto.setName(product.getName());
        dto.setNet(product.getNet());
        dto.setDescription(product.getDescription());

        CategoryResponseDTO dtoCa = new CategoryResponseDTO();
        dtoCa.setCategoryId(product.getCategory().getCategoryId());
        dtoCa.setName(product.getCategory().getName());

        dto.setCategory(dtoCa);

        return dto;
        
    }



    public Product toEntity(ProductRequestDTO dto){
        Product product = new Product();
        product.setName(dto.getName());
        product.setNet(dto.getNet());
        product.setDescription(dto.getDescription());
        
        Category category = new Category();
        category.setCategoryId(dto.getCategory());

        if(dto.getCategory() != null){
            product.setCategory(category);
        }
        
        return product;
    }




    public void updateDtoFromProduct(ProductRequestDTO dto, Product product){
        product.setName(dto.getName());
        product.setNet(dto.getNet());
        product.setDescription(dto.getDescription());

        Category category = new Category();
        category.setCategoryId(product.getCategory().getCategoryId());
        category.setName(product.getCategory().getName());

        product.setCategory(category);
    }

}
