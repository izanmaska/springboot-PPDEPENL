package com.ethan.apiproject.IntegrationTests.RepositoryIntegrationTest;

import com.ethan.apiproject.model.Transactions;
import com.ethan.apiproject.repository.TransactionsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TransactionsRIT {

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Test
    public void testSaveAndFindTransaction() {
        Transactions transaction = new Transactions();
        transaction.setUser1Id(1L);
        transaction.setUser2Id(2L);
        transaction.setUrl("example.com/transaction");

        Transactions savedTransaction = transactionsRepository.save(transaction);
        assertNotNull(savedTransaction.getId());

        Transactions foundTransaction = transactionsRepository.findById(savedTransaction.getId()).orElse(null);
        assertNotNull(foundTransaction);
        assertEquals(1L, foundTransaction.getUser1Id());
        assertEquals(2L, foundTransaction.getUser2Id());
        assertEquals("example.com/transaction", foundTransaction.getUrl());
    }

    @Test
    public void testFindAllTransactions() {
        List<Transactions> transactions = transactionsRepository.findAll();
        assertNotNull(transactions);
        assertTrue(transactions.size() > 0);
    }

    @Test
    public void testDeleteTransaction() {
        Transactions transaction = new Transactions();
        transaction.setUser1Id(1L);
        transaction.setUser2Id(2L);
        transaction.setUrl("example.com/transaction");

        Transactions savedTransaction = transactionsRepository.save(transaction);
        assertNotNull(savedTransaction.getId());

        transactionsRepository.deleteById(savedTransaction.getId());
        assertFalse(transactionsRepository.findById(savedTransaction.getId()).isPresent());
    }

}
