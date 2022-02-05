package com.alkemy.disney.auth.controller;


import com.alkemy.disney.auth.dto.AuthenticationRequest;
import com.alkemy.disney.auth.dto.AuthenticationResponse;
import com.alkemy.disney.auth.dto.UserDto;
import com.alkemy.disney.auth.service.JwtUtil;
import com.alkemy.disney.auth.service.UserDetailsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;



@RestController
@RequestMapping("/auth")
public class UserAuthController {
    @Autowired
    private UserDetailsCustomService userDetailsCustomService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/singup")
    public ResponseEntity<AuthenticationResponse> singUp(@Valid @RequestBody UserDto user) throws Exception{
        userDetailsCustomService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @PostMapping("/singin")
    public ResponseEntity<AuthenticationResponse> singIn(@RequestBody AuthenticationRequest authRequest)throws Exception{
        UserDetails userDetails;

        /*Genero el token con los datos recibidos del inicio de sesion*/
        /*por eso se realiza trycatch para atrapar un error si ocurre*/
        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));

            userDetails=(UserDetails) auth.getPrincipal();
        }catch (BadCredentialsException e){
            throw new Exception("Password o username incorrecto", e);
        }

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }




}
