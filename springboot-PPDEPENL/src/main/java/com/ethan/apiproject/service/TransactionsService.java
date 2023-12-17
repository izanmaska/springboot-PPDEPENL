package com.ethan.apiproject.service;

import com.ethan.apiproject.model.Transactions;
import com.ethan.apiproject.model.enums.Type;
import com.ethan.apiproject.model.User;
import com.ethan.apiproject.repository.TransactionsRepository;
import com.ethan.apiproject.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionsService {
    @Autowired
    private TransactionsRepository transactionsRepository;
    @Autowired
    private UsersRepository usersRepository;

    public Transactions createTransaction(Transactions transactions) {
        User user1 = usersRepository.findById(transactions.getUser1Id()).orElse(null);
        User user2 = usersRepository.findById(transactions.getUser2Id()).orElse(null);

        if (user1 == null || user2 == null) {
            throw new IllegalArgumentException("Invalid user IDs in the transaction");
        }

        if (!(user1.getUserType() == Type.B2C || user2.getUserType() == Type.B2C)) {
            throw new IllegalArgumentException("At least one user must be of type 'B2C'");
        }

        return transactionsRepository.save(transactions);
    }
    public Page<Transactions> transactionsFindAll(Pageable pageable) {
        return transactionsRepository.findAll(pageable);
    }    public void deleteTransaction(Transactions transactions){
        transactionsRepository.delete(transactions);
    }

    public Optional<Transactions> transactionsFindById(UUID id){
        return transactionsRepository.findById(id);
    }
    public List<Transactions> getAllTransactionsByUserId(UUID userId) {
        return transactionsRepository.findByUser1IdOrUser2Id(userId, userId);
    }


    public boolean transactionsExistsById(UUID id) {
        return transactionsRepository.existsById(id);
    }

    public Page<Transactions> listTransactionsByUser(UUID userId, Pageable pageable) {
        List<Transactions> allTransactions = transactionsRepository.findAllByUser1IdOrUser2Id(userId, userId);
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Transactions> paginatedTransactions;

        if (startItem < allTransactions.size()) {
            int endItem = Math.min(startItem + pageSize, allTransactions.size());
            paginatedTransactions = allTransactions.subList(startItem, endItem);
        } else {
            paginatedTransactions = Collections.emptyList();
        }

        return new PageImpl<>(paginatedTransactions, pageable, allTransactions.size());
    }

    public void deleteTransactionsByUser(UUID userId) {
        transactionsRepository.deleteAllByUser1IdOrUser2Id(userId, userId);
    }


}
