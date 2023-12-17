package com.ethan.apiproject.service;

import com.ethan.apiproject.model.User;
import com.ethan.apiproject.model.enums.UserRole;
import com.ethan.apiproject.repository.UsersRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;


@Service
public class UserDetailsServiceImpl implements UserDetailService {

    private final UsersRepository usersRepository;

    public UserDetailsServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        System.out.println("------------------------------------------------");
        System.out.println(user);
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), true, true, true,
                true, getAuthorities(user.getRoles()));
    }

    public User getUserByUserName(String userName){
        return usersRepository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + userName));
    }


    private Collection<GrantedAuthority> getAuthorities(Collection<UserRole> roles) {

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" +role.name()))
                .collect(Collectors.toList());


    }
}
