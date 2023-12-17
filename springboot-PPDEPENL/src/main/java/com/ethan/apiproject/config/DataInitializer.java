package com.ethan.apiproject.config;

import com.ethan.apiproject.model.User;
import com.ethan.apiproject.model.enums.Status;
import com.ethan.apiproject.model.enums.Type;
import com.ethan.apiproject.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private AuthService authService;

    @Override
    public void run(String... args) throws Exception {
        User user = new User(UUID.randomUUID().toString(), "Admin", "P@ssWord123", "Admin@root.com", Status.ACTIVE, Type.B2B,null, LocalDateTime.now(), null);
        authService.registerAdmin(user);
    }
}
