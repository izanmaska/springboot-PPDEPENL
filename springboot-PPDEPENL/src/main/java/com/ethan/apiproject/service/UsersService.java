package com.ethan.apiproject.service;

import com.ethan.apiproject.model.Transactions;
import com.ethan.apiproject.model.Users;
import com.ethan.apiproject.repository.TransactionsRepository;
import com.ethan.apiproject.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Users> usersFindAll(Pageable pageable) {
        return usersRepository.findAll(pageable);
    }    public void deleteUser(Users users){
        usersRepository.delete(users);
    }

    public Optional<Users> userFindById(Long id){
        return usersRepository.findById(id);
    }
    public boolean userExistsById(Long id){return usersRepository.existsById(id);}

}
