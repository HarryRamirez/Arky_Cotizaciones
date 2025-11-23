package com.arky.cotizaciones.mapper;

import org.springframework.stereotype.Component;

import com.arky.cotizaciones.dto.DiscountRequestDTO;
import com.arky.cotizaciones.dto.DiscountResponseDTO;
import com.arky.cotizaciones.model.Discount;

@Component
public class DiscountMapper {

    public DiscountResponseDTO toDto(Discount discount){
        DiscountResponseDTO dto = new DiscountResponseDTO();
        dto.setDicountId(discount.getDiscountId());
        dto.setPercentage(discount.getPercentage());

        return dto;
    }


    
    public Discount toEntity(DiscountRequestDTO dto){
        Discount discount = new Discount();
        discount.setPercentage(dto.getPercentage());

        return discount;
    }
}
