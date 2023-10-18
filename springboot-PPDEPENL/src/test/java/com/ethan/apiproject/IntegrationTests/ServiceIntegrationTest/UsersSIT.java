package com.ethan.apiproject.IntegrationTests.ServiceIntegrationTest;

import com.ethan.apiproject.model.Type;
import com.ethan.apiproject.model.Users;
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
public class UsersSIT {

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
        Users newUser = new Users();
        newUser.setUserName("TestUser");
        newUser.setUserType(Type.B2C);

        ResponseEntity<Users> response = restTemplate.postForEntity(
                baseUrl + "/api/users",
                newUser,
                Users.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Users createdUser = response.getBody();

        assertNotNull(createdUser);
        assertNotNull(createdUser.getId());
        assertEquals("TestUser", createdUser.getUserName());
        assertEquals(Type.B2C, createdUser.getUserType());

    }

    @Test
    public void testListAllUsers() {
        ResponseEntity<Users[]> response = restTemplate.getForEntity(
                baseUrl + "/api/users",
                Users[].class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Users[] users = response.getBody();

        assertNotNull(users);
    }

    @Test
    public void testFindUserById() {
        Users newUser = new Users();
        newUser.setUserName("TestUser");
        newUser.setUserType(Type.B2C);

        ResponseEntity<Users> createResponse = restTemplate.postForEntity(
                baseUrl + "/api/users",
                newUser,
                Users.class
        );

        Users createdUser = createResponse.getBody();
        assert createdUser != null;
        Long userId = createdUser.getId();

        ResponseEntity<Users> findResponse = restTemplate.getForEntity(
                baseUrl + "/api/users/" + userId,
                Users.class
        );

        assertEquals(HttpStatus.OK, findResponse.getStatusCode());
        Users foundUser = findResponse.getBody();

        assertNotNull(foundUser);
        assertEquals(userId, foundUser.getId());
        assertEquals("TestUser", foundUser.getUserName());
        assertEquals(Type.B2C, foundUser.getUserType());

    }
}
