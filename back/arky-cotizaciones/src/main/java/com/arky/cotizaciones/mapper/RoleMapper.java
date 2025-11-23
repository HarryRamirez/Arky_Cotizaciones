package com.arky.cotizaciones.mapper;

import org.springframework.stereotype.Component;

import com.arky.cotizaciones.dto.RoleRequestDTO;
import com.arky.cotizaciones.dto.RoleResponseDTO;
import com.arky.cotizaciones.model.Role;

@Component
public class RoleMapper {

    public RoleResponseDTO toDto(Role role){
        RoleResponseDTO dto = new RoleResponseDTO();
        dto.setRoleId(role.getRoleId());
        dto.setName(role.getName());

        return dto;
    }

    
    public Role toEntity(RoleRequestDTO dto){
        Role role = new Role();
        role.setName(dto.getName());

        return role;
    }
}
