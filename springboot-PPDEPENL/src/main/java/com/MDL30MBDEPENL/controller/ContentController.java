package com.MDL30MBDEPENL.controller;

import com.MDL30MBDEPENL.model.Users;
import com.MDL30MBDEPENL.repository.usersCommunitiesAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentController {
    private final usersCommunitiesAPI repository;

    @Autowired
    public ContentController(usersCommunitiesAPI repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<Users> usersFindAll(){
        return repository.usersFindAll();
    }

    @GetMapping("{id}")
    public Users usersFindById(@PathVariable Integer id){
        return repository.usersFindById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Content not found!"));
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Users users){
        repository.saveUser(users);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Users users, @PathVariable Integer id){
        if(!repository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Content not found!");
        }
        repository.saveUser(users);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        repository.delete(id);
    }
}
