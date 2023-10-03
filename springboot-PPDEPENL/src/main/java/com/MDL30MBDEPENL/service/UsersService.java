package com.MDL30MBDEPENL.service;

import com.MDL30MBDEPENL.model.Users;
import com.MDL30MBDEPENL.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public Users create (Users users){
        return usersRepository.save(users);
    }
    public List<Users> usersFindAll(){
        return usersRepository.findAll();
    }
    public void delete (Users users){
        usersRepository.delete(users);
    }

    public Optional<Users> usersFindById(Long id){
        return usersRepository.findById(id);
    }

}
