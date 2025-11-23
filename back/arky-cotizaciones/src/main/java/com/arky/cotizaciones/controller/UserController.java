package com.arky.cotizaciones.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arky.cotizaciones.config.exception.ResourceNotFoundException;
import com.arky.cotizaciones.dto.RequestUserDTO;
import com.arky.cotizaciones.dto.UserResponseDTO;
import com.arky.cotizaciones.model.User;
import com.arky.cotizaciones.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;


	// Listar usuarios
	@GetMapping("/all")
	public ResponseEntity<List<UserResponseDTO>> getAll() {
		return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
	}



	// Obtener un usuario
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable("id") int userId) {
    	Optional<UserResponseDTO> userDTO = userService.getUser(userId);
    	return 	userDTO.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
        		.orElse( new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



    // Guardar usuario
	@PostMapping("/save")
	public ResponseEntity<User> save(@RequestBody RequestUserDTO userDTO){

		try {
			User user = userService.saveUser(userDTO);
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		}catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

	}









}
