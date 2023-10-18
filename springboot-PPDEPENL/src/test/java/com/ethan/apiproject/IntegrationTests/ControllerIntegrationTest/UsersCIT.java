package com.ethan.apiproject.IntegrationTests.ControllerIntegrationTest;
import com.ethan.apiproject.service.UsersService;
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
public class UsersCIT {

    @LocalServerPort
    private int port;

    @Autowired
    private UsersService usersService;

    private String baseUrl;
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        baseUrl = "http://localhost:" + port;
        restTemplate = new RestTemplate();
    }

    @Test
    public void testCreateUser() {
        com.ethan.apiproject.model.Users newUser = new com.ethan.apiproject.model.Users();
        newUser.setUserName("TestUser");
        newUser.setEmail("testuser@example.com");

        ResponseEntity<com.ethan.apiproject.model.Users> response = restTemplate.postForEntity(
                baseUrl + "/api/users",
                newUser,
                com.ethan.apiproject.model.Users.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        com.ethan.apiproject.model.Users createdUser = response.getBody();

        assertNotNull(createdUser);
        assertNotNull(createdUser.getId());
        assertEquals("TestUser", createdUser.getUserName());
        assertEquals("testuser@example.com", createdUser.getEmail());

    }

    @Test
    public void testListAllUsers() {
        ResponseEntity<com.ethan.apiproject.model.Users[]> response = restTemplate.getForEntity(
                baseUrl + "/api/users",
                com.ethan.apiproject.model.Users[].class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        com.ethan.apiproject.model.Users[] users = response.getBody();

        assertNotNull(users);
    }

    @Test
    public void testFindUserById() {
        com.ethan.apiproject.model.Users newUser = new com.ethan.apiproject.model.Users();
        newUser.setUserName("TestUser");
        newUser.setEmail("testuser@example.com");

        ResponseEntity<com.ethan.apiproject.model.Users> createResponse = restTemplate.postForEntity(
                baseUrl + "/api/users",
                newUser,
                com.ethan.apiproject.model.Users.class
        );

        com.ethan.apiproject.model.Users createdUser = createResponse.getBody();
        assert createdUser != null;
        Long userId = createdUser.getId();

        ResponseEntity<com.ethan.apiproject.model.Users> findResponse = restTemplate.getForEntity(
                baseUrl + "/api/users/" + userId,
                com.ethan.apiproject.model.Users.class
        );

        assertEquals(HttpStatus.OK, findResponse.getStatusCode());
        com.ethan.apiproject.model.Users foundUser = findResponse.getBody();

        assertNotNull(foundUser);
        assertEquals(userId, foundUser.getId());
        assertEquals("TestUser", foundUser.getUserName());
        assertEquals("testuser@example.com", foundUser.getEmail());

    }
}
