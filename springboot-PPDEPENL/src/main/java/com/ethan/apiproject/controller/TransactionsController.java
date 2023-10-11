package com.ethan.apiproject.controller;

import com.ethan.apiproject.model.Transactions;
import com.ethan.apiproject.model.Users;
import com.ethan.apiproject.service.TransactionsService;
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
@RequestMapping("/api/transactions")
public class TransactionsController {

    private final TransactionsService transactionsService;
    @Autowired
    private TransactionsController(TransactionsService transactionsService){
        this.transactionsService = transactionsService;
    }


    @GetMapping("/transactions")
    private ResponseEntity<List<Transactions>> listAllTransactions (){
        return ResponseEntity.ok(transactionsService.transactionsFindAll());
    }
    @GetMapping (value = "/{id}")
    private ResponseEntity<Optional<Transactions>> findTransactionById (@PathVariable ("id") Long id){
        return ResponseEntity.ok(transactionsService.transactionsFindById(id));
    }
    @PostMapping
    private ResponseEntity<Transactions> saveTransaction (@RequestBody Transactions transactions){
        Transactions temp = transactionsService.createTransaction(transactions);
        try {
            return ResponseEntity.created(new URI("api/transactions/"+temp.getId())).body(temp);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateTransaction(@RequestBody Transactions transactions, @PathVariable Long id){
        if(!transactionsService.transactionsExistsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Content not found!");
        }
        transactionsService.createTransaction(transactions);
    }
    @DeleteMapping
    private ResponseEntity<Void> deleteTransaction (@RequestBody Transactions transactions){
        transactionsService.deleteTransaction(transactions);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{userId}/transactions")
    public List<Transactions> listTransactionsByUser(@PathVariable Long userId) {
        return transactionsService.listTransactionsByUser(userId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{userId}/transactions")
    public void deleteTransactionsByUser(@PathVariable Long userId) {
        transactionsService.deleteTransactionsByUser(userId);
    }


}
