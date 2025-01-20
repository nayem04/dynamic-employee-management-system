package com.dynamicemployeemanagementsystem.domain.controllers;

import com.dynamicemployeemanagementsystem.common.exceptions.InvalidCredentialException;
import com.dynamicemployeemanagementsystem.common.routing.Router;
import com.dynamicemployeemanagementsystem.domain.dtos.AuthenticationRequestDto;
import com.dynamicemployeemanagementsystem.domain.dtos.AuthenticationResponseDto;
import com.dynamicemployeemanagementsystem.domain.services.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping(Router.LOGIN)
    public ResponseEntity<AuthenticationResponseDto> login(@Valid @RequestBody AuthenticationRequestDto authenticationRequestDto) throws InvalidCredentialException {
        return ResponseEntity.ok(authenticationService.login(authenticationRequestDto));
    }
}
