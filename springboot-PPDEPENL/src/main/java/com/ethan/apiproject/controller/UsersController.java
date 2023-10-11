package com.ethan.apiproject.controller;

import com.ethan.apiproject.model.Transactions;
import com.ethan.apiproject.model.Users;
import com.ethan.apiproject.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService){
        this.usersService = usersService;
    }

    @GetMapping("/users")
    private ResponseEntity<List<Users>> listAllUsers (){
        return ResponseEntity.ok(usersService.usersFindAll());
    }

    @GetMapping ("/{id}")
    private ResponseEntity<Optional<Users>> findUserById (@PathVariable ("id") Long id){
        return ResponseEntity.ok(usersService.userFindById(id));
    }

    @PostMapping
    private ResponseEntity<Users> saveUser (@RequestBody Users users){
        Users temp = usersService.createUser(users);
        try {
            return ResponseEntity.created(new URI("api/users/"+temp.getId())).body(temp);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateUser(@RequestBody Users users, @PathVariable Long id){
        if(!usersService.userExistsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Content not found!");
        }
        usersService.createUser(users);
    }

    @DeleteMapping
    private ResponseEntity<Void> deleteUsers (@RequestBody Users users){
        usersService.deleteUser(users);
        return ResponseEntity.ok().build();
    }




}
