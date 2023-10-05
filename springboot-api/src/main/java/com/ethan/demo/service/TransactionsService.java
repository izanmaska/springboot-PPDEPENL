package com.ethan.demo.service;

import com.ethan.demo.model.Transactions;
import com.ethan.demo.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionsService {
    @Autowired
    private TransactionsRepository transactionsRepository;

    public Transactions createTransaction(Transactions transactions){
        return transactionsRepository.save(transactions);
    }
    public List<Transactions> transactionsFindAll(){
        return transactionsRepository.findAll();
    }
    public void deleteTransaction(Transactions transactions){
        transactionsRepository.delete(transactions);
    }

    public Optional<Transactions> transactionsFindById(Long id){
        return transactionsRepository.findById(id);
    }
}
