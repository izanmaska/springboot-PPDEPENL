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

    @Test
    public void testSaveAndFindUser() {
        Users user = new Users();
        user.setUserName("testuser");
        user.setEmail("testuser@example.com");

        Users savedUser = usersRepository.save(user);
        assertNotNull(savedUser.getId());

        Users foundUser = usersRepository.findById(savedUser.getId()).orElse(null);
        assertNotNull(foundUser);
        assertEquals("testuser", foundUser.getUserName());
        assertEquals("testuser@example.com", foundUser.getEmail());
    }

    @Test
    public void testFindAllUsers() {
        List<Users> users = usersRepository.findAll();
        assertNotNull(users);
        assertTrue(users.size() > 0);
    }

    @Test
    public void testDeleteUser() {
        Users user = new Users();
        user.setUserName("testuser");
        user.setEmail("testuser@example.com");

        Users savedUser = usersRepository.save(user);
        assertNotNull(savedUser.getId());

        usersRepository.deleteById(savedUser.getId());
        assertFalse(usersRepository.findById(savedUser.getId()).isPresent());
    }

}

