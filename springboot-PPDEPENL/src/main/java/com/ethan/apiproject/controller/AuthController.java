package com.ethan.apiproject.controller;

import com.ethan.apiproject.model.User;
import com.ethan.apiproject.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            User createdUser = authService.registerUser(user);
            if (createdUser == null)
                return ResponseEntity.badRequest().body("User Already Exists!");
            return ResponseEntity.ok(authService.generateTokenForUser(createdUser));
        }catch (Exception e){return ResponseEntity.internalServerError().body("An error occured during signup");}

    }

    @PostMapping("/registerModerator")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> registerModerator(@RequestBody User user){
        User createdModerator = authService.registerModerator(user);
        return ResponseEntity.ok(authService.generateTokenForUser(createdModerator));
    }
}
