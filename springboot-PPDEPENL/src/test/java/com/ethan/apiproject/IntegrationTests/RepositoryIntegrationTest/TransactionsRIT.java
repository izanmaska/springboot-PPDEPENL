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

}
