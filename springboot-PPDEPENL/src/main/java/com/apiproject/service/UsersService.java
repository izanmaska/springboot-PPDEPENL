package com.apiproject.service;

import com.apiproject.model.Users;
import com.apiproject.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public Users createUser(Users users){
        return usersRepository.save(users);
    }
    public List<Users> usersFindAll(){
        return usersRepository.findAll();
    }
    public void deleteUser(Users users){
        usersRepository.delete(users);
    }

    public Optional<Users> userFindById(Long id){
        return usersRepository.findById(id);
    }

}
