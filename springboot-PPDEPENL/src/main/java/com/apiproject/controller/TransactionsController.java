package com.apiproject.controller;

import com.apiproject.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class TransactionsController {
    @Autowired
    private TransactionsService transactionsService;

//    @PostMapping
//    private ResponseEntity<Transactions> saveTransaction (@RequestBody Transactions transactions){
//        Transactions temp = transactionsService.createTransaction(transactions);
//        try {
//            return ResponseEntity.created(new URI("api/users/"+temp.getId())).body(temp);
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }
//    @GetMapping
//    private ResponseEntity<List<Transactions>> listAllTransactions (){
//        return ResponseEntity.ok(transactionsService.transactionsFindAll());
//    }

//    @DeleteMapping
//    private ResponseEntity<Void> deleteTransaction (@RequestBody Transactions transactions){
//        transactionsService.deleteTransaction(transactions);
//        return ResponseEntity.ok().build();
//    }
//    @GetMapping (value = "{id}")
//    private ResponseEntity<Optional<Transactions>> findTransactionById (@PathVariable ("id") Long id){
//        return ResponseEntity.ok(transactionsService.transactionsFindById(id));
//    }
}
