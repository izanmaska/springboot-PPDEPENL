package com.ethan.apiproject.service;

import com.ethan.apiproject.model.Role;
import com.ethan.apiproject.model.enums.Status;
import com.ethan.apiproject.model.User;
import com.ethan.apiproject.repository.UsersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;
import java.util.UUID;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Boolean userExists(String userName){
       return usersRepository.findByUserName(userName).isPresent();
    }
    public User createUser(User user){
        return usersRepository.save(user);
    }
    public User createUserWithRoles(User user, List<Role> roles) {
        user.setRoles(roles);
        return usersRepository.save(user);
    }
    public Page<User> usersFindAll(Pageable pageable) {
        return usersRepository.findAll(pageable);
    }       public void deleteUser(String userId) {
        Optional<User> optionalUser = usersRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
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

    public Optional<User> userFindById(String id){
        return usersRepository.findById(id);
    }
    public boolean userExistsById(String id){return usersRepository.existsById(id);}

}
