package com.MDL30MBDEPENL.controller;

import com.MDL30MBDEPENL.model.Communities;
import com.MDL30MBDEPENL.model.Transactions;
import com.MDL30MBDEPENL.model.Users;
import com.MDL30MBDEPENL.repository.usersCommunitiesAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ContentController {
    private final usersCommunitiesAPI repository;

    @Autowired
    public ContentController(usersCommunitiesAPI repository) {
        this.repository = repository;
    }

    //basic mapping
    @GetMapping("/users")
    public List<Users> usersFindAll(){
        return repository.usersFindAll();
    }
    @GetMapping("/communities")
    public List<Communities> communitiesFindAll() {
        return repository.communitiesFindAll();
    }
    @GetMapping("/transactions")
    public List<Transactions> transactionsFindAll() {
        return repository.transactionsFindAll();
    }

    //find by id
    @GetMapping("/users/{id}")
    public Users usersFindById(@PathVariable Long id){
        return repository.usersFindById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Content not found!"));
    }
    @GetMapping("/communities/{id}")
    public Communities communitiesFindById(@PathVariable Long id) {
        return repository.communitiesFindById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Community not found!"));
    }
    @GetMapping("/transactions/{id}")
    public Transactions transactionsFindById(@PathVariable Long id) {
        return repository.transactionsFindById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found!"));
    }


    //save data

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users")
    public void create(@RequestBody Users users){
        repository.userSave(users);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/communities")
    public void createCommunity(@RequestBody Communities community) {
        repository.communitySave(community);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/transactions")
    public void createTransaction(@RequestBody Transactions transaction) {
        repository.transactionSave(transaction);
    }

    //update data
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/users/{id}")
    public void update(@RequestBody Users users, @PathVariable Long id){
        if(!repository.userExistsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Content not found!");
        }
        repository.userSave(users);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/communities/{id}")
    public void updateCommunity(@RequestBody Communities community, @PathVariable Long id) {
        if (!repository.communityExistsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Community not found!");
        }
        repository.communitySave(community);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/transactions/{id}")
    public void updateTransaction(@RequestBody Transactions transaction, @PathVariable Long id) {
        if (!repository.transactionExistsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found!");
        }
        repository.transactionSave(transaction);
    }


    //delete data
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/users/{id}")
    public void delete(@PathVariable Long id){
        repository.userDelete(id);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/communities/{id}")
    public void deleteCommunity(@PathVariable Long id) {
        if (!repository.communityExistsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Community not found!");
        }
        repository.communityDelete(id);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/transactions/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        if (!repository.transactionExistsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found!");
        }
        repository.transactionDelete(id);
    }

    // ?????????????
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/communities/{communityId}/users/{userId}")
    public void deleteUserFromCommunity(@PathVariable Long communityId, @PathVariable Long userId) {
        repository.deleteUserFromCommunity(userId, communityId);
    }

    @GetMapping("/users/{userId}/transactions")
    public List<Transactions> listTransactionsByUser(@PathVariable Long userId) {
        return repository.listTransactionsByUser(userId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/users/{userId}/transactions")
    public void deleteTransactionsByUser(@PathVariable Long userId) {
        repository.deleteTransactionsByUser(userId);
    }

    @GetMapping("/communities/{communityId}/users")
    public List<Users> listUsersByCommunityId(@PathVariable Long communityId) {
        return repository.listUsersByCommunityId(communityId);
    }

    @GetMapping("/communities/{communityName}/users")
    public List<Users> listUsersByCommunityName(@PathVariable String communityName) {
        return repository.listUsersByCommunityName(communityName);
    }

//    @PostMapping("/transactions")
//    public ResponseEntity<String> createTransaction(@RequestBody TransactionRequest request) {
//        boolean hasB2BUser = usersList.stream()
//                .anyMatch(user -> user.getUserType() == Type.B2B);
//
//        if (hasB2BUser) {
//
//            return ResponseEntity.status(HttpStatus.CREATED).body("Transaction created");
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No B2B users found");
//        }
//    }


}

