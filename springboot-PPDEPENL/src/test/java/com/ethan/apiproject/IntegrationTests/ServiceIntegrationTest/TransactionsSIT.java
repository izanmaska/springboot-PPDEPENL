package com.ethan.apiproject.IntegrationTests.ServiceIntegrationTest;

import com.ethan.apiproject.model.Transactions;
import com.ethan.apiproject.service.TransactionsService;
import com.ethan.apiproject.model.Type;
import com.ethan.apiproject.repository.TransactionsRepository;
import com.ethan.apiproject.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionsSIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TransactionsService transactionsService;

    @Autowired
    private TransactionsRepository transactionsRepository;

    @Autowired
    private UsersRepository usersRepository;

    private String baseUrl;
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        baseUrl = "http://localhost:" + port;
        restTemplate = new RestTemplate();
    }

    @Test
    public void testCreateTransaction() {
        Transactions newTransaction = new Transactions();
        newTransaction.setUser1Id(1L);
        newTransaction.setUser2Id(2L);
        newTransaction.setUser1Type(Type.B2C);
        newTransaction.setUser2Type(Type.B2C);

        ResponseEntity<Transactions> response = restTemplate.postForEntity(
                baseUrl + "/api/transactions",
                newTransaction,
                Transactions.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Transactions createdTransaction = response.getBody();

        assertNotNull(createdTransaction);
        assertNotNull(createdTransaction.getId());
        assertEquals(newTransaction.getUser1Id(), createdTransaction.getUser1Id());
        assertEquals(newTransaction.getUser2Id(), createdTransaction.getUser2Id());

    }

    @Test
    public void testListAllTransactions() {
        ResponseEntity<Transactions[]> response = restTemplate.getForEntity(
                baseUrl + "/api/transactions",
                Transactions[].class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Transactions[] transactions = response.getBody();

        assertNotNull(transactions);
    }

    @Test
    public void testFindTransactionById() {
        Transactions newTransaction = new Transactions();
        newTransaction.setUser1Id(1L);
        newTransaction.setUser2Id(2L);
        newTransaction.setUser1Type(Type.B2C);
        newTransaction.setUser2Type(Type.B2C);

        ResponseEntity<Transactions> createResponse = restTemplate.postForEntity(
                baseUrl + "/api/transactions",
                newTransaction,
                Transactions.class
        );

        Transactions createdTransaction = createResponse.getBody();
        assert createdTransaction != null;
        Long transactionId = createdTransaction.getId();

        ResponseEntity<Transactions> findResponse = restTemplate.getForEntity(
                baseUrl + "/api/transactions/" + transactionId,
                Transactions.class
        );

        assertEquals(HttpStatus.OK, findResponse.getStatusCode());
        Transactions foundTransaction = findResponse.getBody();

        assertNotNull(foundTransaction);
        assertEquals(transactionId, foundTransaction.getId());
        assertEquals(newTransaction.getUser1Id(), foundTransaction.getUser1Id());
        assertEquals(newTransaction.getUser2Id(), foundTransaction.getUser2Id());

    }
}
