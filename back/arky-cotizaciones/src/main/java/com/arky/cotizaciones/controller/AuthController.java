package com.arky.cotizaciones.controller;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arky.cotizaciones.config.jwt.JwtUtil;
import com.arky.cotizaciones.dto.LoginDTO;
import com.arky.cotizaciones.dto.LoginResponseDTO;
import com.arky.cotizaciones.dto.RequestUserDTO;
import com.arky.cotizaciones.model.User;
import com.arky.cotizaciones.repository.UserRepository;
import com.arky.cotizaciones.service.UserService;
import com.arky.cotizaciones.service.impl.UserServiceImpl;


@RestController
@RequestMapping("/auth")
public class AuthController {



    private final UserService userService;
    private final UserServiceImpl userServiceImpl;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public AuthController(UserService userService, UserServiceImpl userServiceImpl, AuthenticationManager authenticationManager, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userServiceImpl = userServiceImpl;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }



    // Registro de usuarios
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RequestUserDTO userDTO) {
        if (userServiceImpl.existsByEmail(userDTO.getEmail())) {
            return ResponseEntity.badRequest().body("Error: El Usuario ya está registrado");
        }

        // Encriptar la contraseña antes de guardarla
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userServiceImpl.saveUser(userDTO);
        return ResponseEntity.ok(Collections.singletonMap("message", "Usuario registrado exitosamente"));
    }






    // Inicio de sesión
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
            );

            // Cargar el usuario autenticado
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Obtener el usuario real desde la base de datos usando el correo
            Optional<User> user = userRepository.findByEmail(userDetails.getUsername());

            User use = user.get();

            String token = jwtUtil.generateToken(authentication.getName());
            LoginResponseDTO logResp = new LoginResponseDTO(token,use.getUserId(),use.getEmail(), use.getFirstname(), use.getLastname());



            return ResponseEntity.ok(logResp);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Error: Usuario no registrado o contraseña incorrecta");
        }
    }
}
