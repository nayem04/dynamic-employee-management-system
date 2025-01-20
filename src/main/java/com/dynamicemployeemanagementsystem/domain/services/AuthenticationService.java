package com.dynamicemployeemanagementsystem.domain.services;

import com.dynamicemployeemanagementsystem.common.exceptions.InvalidCredentialException;
import com.dynamicemployeemanagementsystem.common.utilities.ExceptionUtil;
import com.dynamicemployeemanagementsystem.common.utilities.JwtUtil;
import com.dynamicemployeemanagementsystem.domain.dtos.AuthenticationRequestDto;
import com.dynamicemployeemanagementsystem.domain.dtos.AuthenticationResponseDto;
import com.dynamicemployeemanagementsystem.domain.entities.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponseDto login(AuthenticationRequestDto authenticationRequestDto) throws InvalidCredentialException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequestDto.getUsername(),
                        authenticationRequestDto.getPassword()));

        if (authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();
            Collection<String> roles = user.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority).toList();

            AuthenticationResponseDto authenticationResponseDto = new AuthenticationResponseDto();
            authenticationResponseDto.setAccessToken(JwtUtil.generateToken(user.getUsername(), roles));

            return authenticationResponseDto;
        }

        throw ExceptionUtil.getInvalidCredentialException(authenticationRequestDto.getUsername(), authenticationRequestDto.getPassword());
    }
}
