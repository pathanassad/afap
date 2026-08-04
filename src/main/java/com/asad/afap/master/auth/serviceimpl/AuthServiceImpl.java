package com.asad.afap.master.auth.serviceimpl;


import com.asad.afap.master.auth.dto.LoginRequest;
import com.asad.afap.master.auth.dto.LoginResponse;
import com.asad.afap.master.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Override
    public LoginResponse login(LoginRequest request){
        return null;
    }


}
