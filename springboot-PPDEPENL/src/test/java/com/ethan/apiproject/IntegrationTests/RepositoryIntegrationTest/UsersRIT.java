package com.ethan.apiproject.IntegrationTests.RepositoryIntegrationTest;

import com.ethan.apiproject.model.Users;
import com.ethan.apiproject.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UsersRIT {

    @Autowired
    private UsersRepository usersRepository;


}
