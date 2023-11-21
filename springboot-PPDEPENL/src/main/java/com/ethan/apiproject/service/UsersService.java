package com.ethan.apiproject.service;

import com.ethan.apiproject.model.enums.Status;
import com.ethan.apiproject.model.enums.UserRole;
import com.ethan.apiproject.model.Users;
import com.ethan.apiproject.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;



    public Users createUser(Users users){
        return usersRepository.save(users);
    }
    public Users createUserWithRoles(Users user, Set<UserRole> roles) {
        user.setRoles(roles);
        return usersRepository.save(user);
    }
    public Page<Users> usersFindAll(Pageable pageable) {
        return usersRepository.findAll(pageable);
    }       public void deleteUser(UUID userId) {
        Optional<Users> optionalUser = usersRepository.findById(userId);

        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            user.setUserName(null);
           String[] emailParts = user.getEmail().split("@");
            if (emailParts.length > 1) {
                user.setEmail("@" + emailParts[1]);
            }
            user.setStatus(Status.INACTIVE);
            user.setDateUpdated(LocalDateTime.now());
            usersRepository.save(user);
        } else {
            throw new RuntimeException("User with ID " + userId + " not found");
        }
    }

    public Optional<Users> userFindById(UUID id){
        return usersRepository.findById(id);
    }
    public boolean userExistsById(UUID id){return usersRepository.existsById(id);}

}
