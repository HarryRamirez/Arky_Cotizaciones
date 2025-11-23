package com.arky.cotizaciones.mapper;



import org.springframework.stereotype.Component;

import com.arky.cotizaciones.dto.CategoryRequestDTO;
import com.arky.cotizaciones.dto.CategoryResponseDTO;
import com.arky.cotizaciones.model.Category;

@Component
public class CategoryMapper {


    public CategoryResponseDTO toDto(Category category){
        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setCategoryId(category.getCategoryId());
        dto.setName(category.getName());

        return dto;
    }


    public Category toEntity(CategoryRequestDTO dto){
        Category category = new Category();
        category.setName(dto.getName());

        return category;
    }



    public void updateDtoFromCategory(CategoryRequestDTO dto, Category category){
        category.setName(dto.getName());
    }
}
