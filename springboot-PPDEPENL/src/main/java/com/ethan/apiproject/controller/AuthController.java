package com.ethan.apiproject.controller;

import com.ethan.apiproject.model.AuthRequest;
import com.ethan.apiproject.model.Users;
import com.ethan.apiproject.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody AuthRequest authRequest) {
        String jwt = authService.authenticateAndGenerateToken(authRequest.getUsername(), authRequest.getPassword());
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Users user) {
        Users createdUser = authService.registerUser(user);
        String jwt = authService.generateTokenForUser(createdUser);
        return ResponseEntity.ok(jwt);
    }
}
