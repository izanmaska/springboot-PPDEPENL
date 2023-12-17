package com.ethan.apiproject.security;

import com.ethan.apiproject.model.AuthRequest;
import com.ethan.apiproject.service.UserDetailService;
import com.ethan.apiproject.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.ethan.apiproject.model.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthRequest authRequest;
    private final UserDetailService userDetailsService;
    private final JwtUtil jwtUtil;


    public AuthenticationFilter(UserDetailService userDetailsService, JwtUtil jwtUtil) {
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            authRequest = new ObjectMapper().readValue(request.getInputStream(), AuthRequest.class);
            return getAuthenticationManager()
                    .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        User user = userDetailsService.getUserByUserName(authResult.getName());
        response.getWriter().print(mapper.writeValueAsString(jwtUtil.generateToken(user)));
        }
}

