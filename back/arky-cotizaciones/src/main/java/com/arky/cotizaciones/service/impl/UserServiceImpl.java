package com.arky.cotizaciones.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arky.cotizaciones.config.exception.ResourceNotFoundException;
import com.arky.cotizaciones.dto.RequestUserDTO;
import com.arky.cotizaciones.dto.UserResponseDTO;
import com.arky.cotizaciones.model.Role;
import com.arky.cotizaciones.model.User;
import com.arky.cotizaciones.repository.RoleRepository;
import com.arky.cotizaciones.repository.UserRepository;
import com.arky.cotizaciones.service.UserService;







@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository ;

	@Autowired
	private RoleRepository roleRepository;




	// Listar usuarios
	@Override
	public List<UserResponseDTO> getAll(){
		return userRepository.findAll().stream()
				.map(user -> new UserResponseDTO(
						user.getUserId(),
						user.getFirstname(),
						user.getLastname(),
						user.getEmail(),
						user.getPassword(),
						user.getRole().getName()

						)).collect(Collectors.toList());
	}



	// Obtener usuario
	@Override
	public Optional<UserResponseDTO> getUser(int userId) {
        return userRepository.findById(userId)
        		.map(user -> new UserResponseDTO(
        				user.getUserId(),
        				user.getFirstname(),
        				user.getLastname(),
        				user.getEmail(),
        				user.getPassword(),
        				user.getRole().getName()
        				));
	}



	// Comprobar si existe usuario
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }




    // Guardar usuario
	@Override
	public User saveUser(RequestUserDTO userDTO) {


        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalStateException("Email in use");
        }

		Role role = roleRepository.findById(userDTO.getRoleId())
				.orElseThrow(() -> new ResourceNotFoundException("Role not found"));

		User user = new User();
		user.setFirstname(userDTO.getFirstname());
		user.setLastname(userDTO.getLastname());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setRole(role);

		return userRepository.save(user);
	}







}
