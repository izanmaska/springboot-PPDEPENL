package com.MDL30MBDEPENL.controller;

import com.MDL30MBDEPENL.model.Users;
import com.MDL30MBDEPENL.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class UsersController {
    @Autowired
    private UsersService usersService;

    @PostMapping
    private ResponseEntity<Users> save (@RequestBody Users users){
        Users temp = usersService.createUser(users);
        try {
            return ResponseEntity.created(new URI("api/users/"+temp.getId())).body(temp);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping
    private ResponseEntity<List<Users>> listAllUsers (){
        return ResponseEntity.ok(usersService.usersFindAll());
    }

    @DeleteMapping
    private ResponseEntity<Void> deleteUsers (@RequestBody Users users){
        usersService.deleteUser(users);
        return ResponseEntity.ok().build();
    }
    @GetMapping (value = "{id}")
    private ResponseEntity<Optional<Users>> findUserById (@PathVariable ("id") Long id){
        return ResponseEntity.ok(usersService.userFindById(id));
    }


}
