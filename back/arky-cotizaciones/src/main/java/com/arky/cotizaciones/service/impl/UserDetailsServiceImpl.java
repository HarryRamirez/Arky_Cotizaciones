package com.arky.cotizaciones.service.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.arky.cotizaciones.model.User;
import com.arky.cotizaciones.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	  @Autowired
	  private final UserRepository userRepository;



	    public UserDetailsServiceImpl(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }


	    // Inicio se sesion de usuario
	    @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        // Buscar el usuario por email en la base de datos
	        User user = userRepository.findByEmail(email)
	                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con ese email: " + email));

	        // Devolver un UserDetails que Spring Security pueda usar
	        return new org.springframework.security.core.userdetails.User(
	                user.getEmail(),
	                user.getPassword(),
	                Collections.singleton(user.getRole())
	        );
	    }
}
