package com.ethan.apiproject.service;

import com.ethan.apiproject.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDetailService extends UserDetailsService {
    User getUserByUserName(String userName);
    UserDetails loadUserByUsername(String username);
}
