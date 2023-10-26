package com.ethan.apiproject.IntegrationTests.APIIntegrationTests;
import com.ethan.apiproject.model.Type;
import com.ethan.apiproject.model.Users;
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

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsersAPITests {
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
        ResponseEntity<Page<Users>> response = restTemplate.exchange(
                baseUrl + "/api/users",
                org.springframework.http.HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Page<Users>>() {}
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Page<Users> usersPage = response.getBody();

        assertNotNull(usersPage);
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
    @Test
    public void testUpdateUser() {
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

        newUser.setUserName("UpdatedUserName");
        newUser.setEmail("updateduser@example.com");

        restTemplate.put(baseUrl + "/api/users/" + userId, newUser);

        ResponseEntity<com.ethan.apiproject.model.Users> updatedResponse = restTemplate.getForEntity(
                baseUrl + "/api/users/" + userId,
                com.ethan.apiproject.model.Users.class
        );

        assertEquals(HttpStatus.OK, updatedResponse.getStatusCode());
        com.ethan.apiproject.model.Users updatedUser = updatedResponse.getBody();

        assertNotNull(updatedUser);
        assertEquals(userId, updatedUser.getId());
        assertEquals("UpdatedUserName", updatedUser.getUserName());
        assertEquals("updateduser@example.com", updatedUser.getEmail());
    }
    @Test
    public void testDeleteUser() {
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

        restTemplate.delete(baseUrl + "/api/users/" + userId);

        ResponseEntity<com.ethan.apiproject.model.Users> getResponse = restTemplate.getForEntity(
                baseUrl + "/api/users/" + userId,
                com.ethan.apiproject.model.Users.class
        );

        assertEquals(HttpStatus.NOT_FOUND, getResponse.getStatusCode());
        assertNull(getResponse.getBody());
    }



}
