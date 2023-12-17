package com.ethan.apiproject.controller;

import com.ethan.apiproject.model.User;
import com.ethan.apiproject.service.UsersService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;
    public UsersController(UsersService usersService){
        this.usersService = usersService;
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('MOD') or hasRole('ADMIN')")
    private ResponseEntity<Page<User>> listAllUsers(Pageable pageable) {
        Page<User> usersPage = usersService.usersFindAll(pageable);
        return ResponseEntity.ok(usersPage);
    }


    @GetMapping ("/{id}")
    private ResponseEntity<Optional<User>> findUserById (@PathVariable ("id") String id){
        return ResponseEntity.ok(usersService.userFindById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('MOD') or hasRole('ADMIN')")

    private ResponseEntity<User> saveUser (@RequestBody User user){
        User temp = usersService.createUser(user);
        try {
            return ResponseEntity.created(new URI("api/users/"+temp.getId())).body(temp);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateUser(@RequestBody User user, @PathVariable String id){
        if(!usersService.userExistsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Content not found!");
        }
        usersService.createUser(user);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        usersService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }




}
