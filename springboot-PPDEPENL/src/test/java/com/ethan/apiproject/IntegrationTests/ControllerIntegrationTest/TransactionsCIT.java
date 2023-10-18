package com.ethan.apiproject.IntegrationTests.ControllerIntegrationTest;
import com.ethan.apiproject.service.TransactionsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionsCIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TransactionsService transactionsService;

    private String baseUrl;
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        baseUrl = "http://localhost:" + port;
        restTemplate = new RestTemplate();
    }

    @Test
    public void testCreateTransaction() {
        com.ethan.apiproject.model.Transactions newTransaction = new com.ethan.apiproject.model.Transactions();
        newTransaction.setUser1Id(1L);
        newTransaction.setUser2Id(2L);
        newTransaction.setUrl("http://example.com");

        ResponseEntity<com.ethan.apiproject.model.Transactions> response = restTemplate.postForEntity(
                baseUrl + "/api/transactions",
                newTransaction,
                com.ethan.apiproject.model.Transactions.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        com.ethan.apiproject.model.Transactions createdTransaction = response.getBody();

        assertNotNull(createdTransaction);
        assertNotNull(createdTransaction.getId());
        assertEquals(1L, createdTransaction.getUser1Id());
        assertEquals(2L, createdTransaction.getUser2Id());
        assertEquals("http://example.com", createdTransaction.getUrl());

    }

    @Test
    public void testListAllTransactions() {
        ResponseEntity<com.ethan.apiproject.model.Transactions[]> response = restTemplate.getForEntity(
                baseUrl + "/api/transactions",
                com.ethan.apiproject.model.Transactions[].class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        com.ethan.apiproject.model.Transactions[] transactions = response.getBody();

        assertNotNull(transactions);
    }

    @Test
    public void testFindTransactionById() {
        com.ethan.apiproject.model.Transactions newTransaction = new com.ethan.apiproject.model.Transactions();
        newTransaction.setUser1Id(1L);
        newTransaction.setUser2Id(2L);
        newTransaction.setUrl("http://example.com");

        ResponseEntity<com.ethan.apiproject.model.Transactions> createResponse = restTemplate.postForEntity(
                baseUrl + "/api/transactions",
                newTransaction,
                com.ethan.apiproject.model.Transactions.class
        );

        com.ethan.apiproject.model.Transactions createdTransaction = createResponse.getBody();
        assert createdTransaction != null;
        Long transactionId = createdTransaction.getId();

        ResponseEntity<com.ethan.apiproject.model.Transactions> findResponse = restTemplate.getForEntity(
                baseUrl + "/api/transactions/" + transactionId,
                com.ethan.apiproject.model.Transactions.class
        );

        assertEquals(HttpStatus.OK, findResponse.getStatusCode());
        com.ethan.apiproject.model.Transactions foundTransaction = findResponse.getBody();

        assertNotNull(foundTransaction);
        assertEquals(transactionId, foundTransaction.getId());
        assertEquals(1L, foundTransaction.getUser1Id());
        assertEquals(2L, foundTransaction.getUser2Id());
        assertEquals("http://example.com", foundTransaction.getUrl());

    }
}
