package com.example.demo.config;

import java.util.Map;

import com.example.demo.model.User;

public interface JWTTokenGenerator {

    Map<String, String> generateToken(User user);
}
