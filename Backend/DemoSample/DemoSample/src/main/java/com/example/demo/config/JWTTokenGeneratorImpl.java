package com.example.demo.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;

@Service
public class JWTTokenGeneratorImpl implements JWTTokenGenerator {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwttoken.message}")
    private String message;

    @Override
    public Map<String, String> generateToken(User user) {
        // Generate JWT token
        Map<String, Object> userdata = new HashMap<>();
        userdata.put("email", user.getEmail());
        userdata.put("password", user.getPassword());

        String jwtToken = Jwts.builder()
                .setClaims(userdata)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();

        // Return token and message
        Map<String, String> jwtTokenMap = new HashMap<>();
        jwtTokenMap.put("token", jwtToken);
        jwtTokenMap.put("message", message);
        return jwtTokenMap;
    }
}
