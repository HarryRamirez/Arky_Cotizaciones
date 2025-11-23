package com.arky.cotizaciones.mapper;

import org.springframework.stereotype.Component;

import com.arky.cotizaciones.dto.UserRequestDTO;
import com.arky.cotizaciones.dto.UserResponseDTO;
import com.arky.cotizaciones.model.Role;
import com.arky.cotizaciones.model.User;

@Component
public class UserMapper {

    public UserResponseDTO toDto(User user){
        UserResponseDTO dto = new UserResponseDTO();
        dto.setUserId(user.getUserId());
        dto.setFirstname(user.getFirstname());
        dto.setLastname(user.getLastname());
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setRoleName(user.getRole().getName());

        return dto;
    }


    public User toEntity(UserRequestDTO dto){
        User user = new User();
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        Role role = new Role();
        role.setRoleId(dto.getRoleId());
        user.setRole(role);

        return user;
    }
}
