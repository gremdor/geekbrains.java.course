package com.geekbrains.springweb.controllers;

import com.geekbrains.springweb.dto.JwtRequest;
import com.geekbrains.springweb.dto.JwtResponse;
import com.geekbrains.springweb.entities.User;
import com.geekbrains.springweb.exceptions.AppError;
import com.geekbrains.springweb.services.UserService;
import com.geekbrains.springweb.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PutMapping("/auth/signup")
    public ResponseEntity<?> createUserAndLogin(@RequestBody JwtRequest authRequest) {
        log.info("PUT Method request: "+ authRequest.toString());

        if (userService.existsByUsername(authRequest.getUsername())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "User with this name is already registered"), HttpStatus.BAD_REQUEST);
        }

        userService.createUser(authRequest.getUsername(), authRequest.getPassword());
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

//    @GetMapping("/auth/users")
//    public List<User> getUsers () {
//        return userService.findAll();
//    }

}
