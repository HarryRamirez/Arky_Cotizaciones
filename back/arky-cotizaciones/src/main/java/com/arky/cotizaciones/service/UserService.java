package com.arky.cotizaciones.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.arky.cotizaciones.dto.RequestUserDTO;
import com.arky.cotizaciones.dto.UserResponseDTO;
import com.arky.cotizaciones.model.User;




@Service
public interface UserService {


	List<UserResponseDTO> getAll();

	User saveUser(RequestUserDTO userDTO);

    Optional<UserResponseDTO> getUser(int userId);



}
