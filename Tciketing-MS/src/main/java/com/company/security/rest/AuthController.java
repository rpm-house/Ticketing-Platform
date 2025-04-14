package com.company.security.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.dto.AuthResponseDTO;
import com.company.dto.LoginDTO;
import com.company.security.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

	@Autowired
    private AuthService authService;

	@Operation(summary = "Get Token for users", description = "Returns JWT Token.")
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO){

        //01 - Receive the token from AuthService
        String token = authService.login(loginDTO);

        //02 - Set the token as a response using JwtAuthResponse Dto class
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setAccessToken(token);

        log.info("Token  Generated. ");
        //03 - Return the response to the user
        return new ResponseEntity<>(authResponseDTO, HttpStatus.OK);
    }
    
    
}
