package com.ethan.apiproject.IntegrationTests.APIIntegrationTests;
import com.ethan.apiproject.model.enums.Type;
import com.ethan.apiproject.model.User;
import com.ethan.apiproject.service.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserAPITests {
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
        User newUser = new User();
        newUser.setUserName("TestUser");
        newUser.setUserType(Type.B2C);

        ResponseEntity<User> response = restTemplate.postForEntity(
                baseUrl + "/api/users",
                newUser,
                User.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        User createdUser = response.getBody();

        assertNotNull(createdUser);
        assertNotNull(createdUser.getId());
        assertEquals("TestUser", createdUser.getUserName());
        assertEquals(Type.B2C, createdUser.getUserType());
    }

    @Test
    public void testListAllUsers() {
        ResponseEntity<Page<User>> response = restTemplate.exchange(
                baseUrl + "/api/users",
                org.springframework.http.HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Page<User>>() {}
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Page<User> usersPage = response.getBody();

        assertNotNull(usersPage);
    }

    @Test
    public void testFindUserById() {
        User newUser = new User();
        newUser.setUserName("TestUser");
        newUser.setUserType(Type.B2C);

        ResponseEntity<User> createResponse = restTemplate.postForEntity(
                baseUrl + "/api/users",
                newUser,
                User.class
        );

        User createdUser = createResponse.getBody();
        assert createdUser != null;
        UUID userId = UUID.fromString(createdUser.getId());

        ResponseEntity<User> findResponse = restTemplate.getForEntity(
                baseUrl + "/api/users/" + userId,
                User.class
        );

        assertEquals(HttpStatus.OK, findResponse.getStatusCode());
        User foundUser = findResponse.getBody();

        assertNotNull(foundUser);
        assertEquals(userId, foundUser.getId());
        assertEquals("TestUser", foundUser.getUserName());
        assertEquals(Type.B2C, foundUser.getUserType());
    }
    @Test
    public void testUpdateUser() {
        User newUser = new User();
        newUser.setUserName("TestUser");
        newUser.setEmail("testuser@example.com");

        ResponseEntity<User> createResponse = restTemplate.postForEntity(
                baseUrl + "/api/users",
                newUser,
                User.class
        );

        User createdUser = createResponse.getBody();
        assert createdUser != null;
        UUID userId = UUID.fromString(createdUser.getId());

        newUser.setUserName("UpdatedUserName");
        newUser.setEmail("updateduser@example.com");

        restTemplate.put(baseUrl + "/api/users/" + userId, newUser);

        ResponseEntity<User> updatedResponse = restTemplate.getForEntity(
                baseUrl + "/api/users/" + userId,
                User.class
        );

        assertEquals(HttpStatus.OK, updatedResponse.getStatusCode());
        User updatedUser = updatedResponse.getBody();

        assertNotNull(updatedUser);
        assertEquals(userId, updatedUser.getId());
        assertEquals("UpdatedUserName", updatedUser.getUserName());
        assertEquals("updateduser@example.com", updatedUser.getEmail());
    }
    @Test
    public void testDeleteUser() {
        User newUser = new User();
        newUser.setUserName("TestUser");
        newUser.setEmail("testuser@example.com");

        ResponseEntity<User> createResponse = restTemplate.postForEntity(
                baseUrl + "/api/users",
                newUser,
                User.class
        );

        User createdUser = createResponse.getBody();
        assert createdUser != null;
        UUID userId = UUID.fromString(createdUser.getId());

        restTemplate.delete(baseUrl + "/api/users/" + userId);

        ResponseEntity<User> getResponse = restTemplate.getForEntity(
                baseUrl + "/api/users/" + userId,
                User.class
        );

        assertEquals(HttpStatus.NOT_FOUND, getResponse.getStatusCode());
        assertNull(getResponse.getBody());
    }



}
