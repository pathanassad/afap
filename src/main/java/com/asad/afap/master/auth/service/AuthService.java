package com.asad.afap.master.auth.service;

import com.asad.afap.master.auth.dto.LoginRequest;
import com.asad.afap.master.auth.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);

}
