package com.ethan.apiproject.controller;

import com.ethan.apiproject.model.Transactions;
import com.ethan.apiproject.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/transactions")
public class TransactionsController {

    private final TransactionsService transactionsService;
    public TransactionsController(TransactionsService transactionsService){
        this.transactionsService = transactionsService;
    }


    @GetMapping("/transactions")
    @PreAuthorize("hasRole('MOD') or hasRole('ADMIN')")
    private ResponseEntity<Page<Transactions>> listAllTransactions(Pageable pageable) {
        Page<Transactions> transactionsPage = transactionsService.transactionsFindAll(pageable);
        return ResponseEntity.ok(transactionsPage);
    }
    @GetMapping (value = "/{id}")
    private ResponseEntity<Optional<Transactions>> findTransactionById (@PathVariable ("id") UUID id){
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
    @PreAuthorize("hasRole('MOD') or hasRole('ADMIN')")
    public void updateTransaction(@RequestBody Transactions transactions, @PathVariable UUID id){
        if(!transactionsService.transactionsExistsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Content not found!");
        }
        transactionsService.createTransaction(transactions);
    }
    @DeleteMapping
    @PreAuthorize("hasRole('MOD') or hasRole('ADMIN')")
    private ResponseEntity<Void> deleteTransaction (@RequestBody Transactions transactions){
        transactionsService.deleteTransaction(transactions);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{userId}/transactions")
    public ResponseEntity<Page<Transactions>> listTransactionsByUser(@PathVariable UUID userId,
                                                                     @RequestParam(name = "page", defaultValue = "0") int page,
                                                                     @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<Transactions> transactionsPage = transactionsService.listTransactionsByUser(userId, PageRequest.of(page, size));
        return ResponseEntity.ok(transactionsPage);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{userId}/transactions")
    public void deleteTransactionsByUser(@PathVariable UUID userId) {
        transactionsService.deleteTransactionsByUser(userId);
    }


}
