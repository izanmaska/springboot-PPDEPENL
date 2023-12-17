package com.ethan.apiproject.service;

import com.ethan.apiproject.model.Role;
import com.ethan.apiproject.model.enums.UserRole;
import com.ethan.apiproject.model.User;
import com.ethan.apiproject.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UsersService usersService;

    public AuthService(JwtUtil jwtUtil, PasswordEncoder passwordEncoder, UsersService usersService) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.usersService = usersService;
    }
    public String generateTokenForUser(User user) {
        return jwtUtil.generateToken(user);
    }

    public User registerUser(User user) {
        if (usersService.userExists(user.getUserName()))
            return null;
        // add user role by default
        user.setRoles(List.of(new Role(UserRole.USER)));

        // Encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return usersService.createUser(user);
    }

    public User registerModerator(User user) {
        // add user role by default
        user.setRoles(List.of(new Role(UserRole.MOD)));

        // Encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return usersService.createUser(user);
    }

    public User registerAdmin(User user){
        user.setRoles(List.of(new Role(UserRole.ADMIN)));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersService.createUser(user);
    }
}
