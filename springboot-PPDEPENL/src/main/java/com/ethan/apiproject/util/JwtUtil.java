package com.ethan.apiproject.util;

import com.ethan.apiproject.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final SecretKey secret;
    private final Long expiration;

    public JwtUtil(Environment environment) {
        this.secret = Keys.hmacShaKeyFor(environment.getProperty("jwt.secret").getBytes()) ;
        this.expiration = environment.getProperty("jwt.expiration-time", Long.class);
    }

    public String generateToken(User user) {
        var authoroties = new HashMap<String, Object>();
        authoroties.put("authorities", user.getRoles());

        return Jwts.builder()
                .setSubject(user.getUserName())
                .addClaims(authoroties)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(secret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(secret).build();
            Jws<Claims> jws =jwtParser.parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String extractUserName(String token){
        try {
            JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(secret).build();
            Jws<Claims> jws =jwtParser.parseClaimsJws(token);

            String username = jws.getBody().getSubject();
            return username;
        } catch (Exception e) {
            return null;
        }
    }
}