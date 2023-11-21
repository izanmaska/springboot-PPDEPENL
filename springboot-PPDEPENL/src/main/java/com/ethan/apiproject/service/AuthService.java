package com.ethan.apiproject.service;

import com.ethan.apiproject.model.Role;
import com.ethan.apiproject.model.enums.UserRole;
import com.ethan.apiproject.model.Users;
import com.ethan.apiproject.repository.RoleRepository;
import com.ethan.apiproject.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsersService usersService;
    @Autowired
    private RoleRepository roleRepository;
    public String authenticateAndGenerateToken(String username, String password) {
        // Auth user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        // Update SecurityContextHolder with user
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return jwtUtil.generateToken(userDetails.getUsername());
    }

    public String generateTokenForUser(Users user) {
        return jwtUtil.generateToken(user.getUserName());
    }

    public Users registerUser(Users user) {
        Set<Role> roles = new HashSet<>();

        roleRepository.findByName(UserRole.USER)
                .ifPresent(roles::add);

        // any other necessary fields?
        user.setRoles(roles);

        // Encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return usersService.createUser(user);
    }
}
