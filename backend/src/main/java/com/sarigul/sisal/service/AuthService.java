package com.sarigul.sisal.service;

import com.sarigul.sisal.entity.Auth;
import com.sarigul.sisal.entity.model.AuthDto;
import com.sarigul.sisal.entity.model.RegisterRequest;

import java.util.Optional;

public interface AuthService {
    AuthDto register(RegisterRequest request) throws Exception;

    Optional<Auth> findByEmail(String email);
}