package com.asad.afap.master.auth.serviceimpl;


import com.asad.afap.master.auth.dto.LoginRequest;
import com.asad.afap.master.auth.dto.LoginResponse;
import com.asad.afap.master.auth.service.AuthService;
import com.asad.afap.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public LoginResponse login(LoginRequest request){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));
        String token = jwtService.generateToken(Map.of(), authentication.getName());

        return new LoginResponse(token, "Bearer", jwtService.getExpiration());


    }



}
