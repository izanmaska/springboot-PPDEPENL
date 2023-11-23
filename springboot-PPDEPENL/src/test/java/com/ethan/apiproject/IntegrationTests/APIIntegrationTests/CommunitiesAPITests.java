package com.ethan.apiproject.IntegrationTests.APIIntegrationTests;

import com.ethan.apiproject.model.Role;
import com.ethan.apiproject.model.enums.UserRole;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ethan.apiproject.model.Communities;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.ethan.apiproject.model.PagedResponse;
import com.ethan.apiproject.model.Users;
import org.springframework.http.HttpMethod;
import org.springframework.core.ParameterizedTypeReference;


import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommunitiesAPITests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private final String baseUrl = "http://localhost";

    @Test
    public void testCreateCommunity() {
        Communities newCommunity = new Communities();
        newCommunity.setName("Test Community");
        newCommunity.setOwnerUsername("Owner");

        ResponseEntity<Communities> response = restTemplate.postForEntity(
                baseUrl + ":" + port + "/api/communities",
                newCommunity,
                Communities.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Communities createdCommunity = response.getBody();
        assertNotNull(createdCommunity);
        assertNotNull(createdCommunity.getId());
        assertEquals("Test Community", createdCommunity.getName());
        assertEquals("Owner", createdCommunity.getOwnerUsername());
    }

    @Test
    public void testListAllCommunities() {
        ResponseEntity<PagedResponse<Communities>> response = restTemplate.exchange(
                baseUrl + ":" + port + "/api/communities",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PagedResponse<Communities>>() {}
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        PagedResponse<Communities> communitiesPage = response.getBody();
        assertNotNull(communitiesPage);

        assertEquals(0, communitiesPage.getCurrentPage());
        assertEquals(10, communitiesPage.getPageSize());
        assertEquals(20, communitiesPage.getTotalElements());
        assertEquals(2, communitiesPage.getTotalPages());

        assertNotNull(communitiesPage.getContent());
    }
    @Test
    public void testListAllCommunitiesAsAdmin() {
        Set<UserRole> adminRoles = new HashSet<>();
        adminRoles.add(UserRole.ADMIN);

        Users adminUser = Users.createTestUser("admin", adminRoles);
        restTemplate = restTemplate.withBasicAuth(adminUser.getUserName(), "password");

        ResponseEntity<PagedResponse<Communities>> response = restTemplate.exchange(
                baseUrl + ":" + port + "/api/communities",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PagedResponse<Communities>>() {}
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        PagedResponse<Communities> communitiesPage = response.getBody();
        assertNotNull(communitiesPage);
    }


    @Test
    public void testListAllCommunitiesAsRegularUser() {
        Set<UserRole> regularUserRoles = new HashSet<>();
        regularUserRoles.add(UserRole.USER);

        Users regularUser = Users.createTestUser("user", regularUserRoles);
        restTemplate = restTemplate.withBasicAuth(regularUser.getUserName(), "password");

        ResponseEntity<PagedResponse<Communities>> response = restTemplate.exchange(
                baseUrl + ":" + port + "/api/communities",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PagedResponse<Communities>>() {}
        );

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void testDeleteCommunity() {
        Communities newCommunity = new Communities();
        newCommunity.setName("Test Community");
        newCommunity.setOwnerUsername("Owner");

        ResponseEntity<Communities> createResponse = restTemplate.postForEntity(
                baseUrl + ":" + port + "/api/communities",
                newCommunity,
                Communities.class
        );

        assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());
        Communities createdCommunity = createResponse.getBody();

        assert createdCommunity != null;

        restTemplate.delete(baseUrl + ":" + port + "/api/communities/" + createdCommunity.getId());

        ResponseEntity<Communities> getResponse = restTemplate.getForEntity(
                baseUrl + ":" + port + "/api/communities/" + createdCommunity.getId(),
                Communities.class
        );

        assertEquals(HttpStatus.NOT_FOUND, getResponse.getStatusCode());
        assertNull(getResponse.getBody());
    }

    @Test
    public void testUpdateCommunity() {
        Communities newCommunity = new Communities();
        newCommunity.setName("Test Community");
        newCommunity.setOwnerUsername("Owner");

        ResponseEntity<Communities> createResponse = restTemplate.postForEntity(
                baseUrl + "/api/communities",
                newCommunity,
                Communities.class
        );

        Communities createdCommunity = createResponse.getBody();
        assert createdCommunity != null;
        UUID communityId = createdCommunity.getId();

        newCommunity.setName("Updated Community Name");
        newCommunity.setOwnerUsername("Updated Owner");

        restTemplate.put(baseUrl + "/api/communities/" + communityId, newCommunity);

        ResponseEntity<Communities> updatedResponse = restTemplate.getForEntity(
                baseUrl + "/api/communities/" + communityId,
                Communities.class
        );

        assertEquals(HttpStatus.OK, updatedResponse.getStatusCode());
        Communities updatedCommunity = updatedResponse.getBody();

        assertNotNull(updatedCommunity);
        assertEquals(communityId, updatedCommunity.getId());
        assertEquals("Updated Community Name", updatedCommunity.getName());
        assertEquals("Updated Owner", updatedCommunity.getOwnerUsername());
    }

    @Test
    public void testListCommunityMembers() {
        Communities newCommunity = new Communities();
        newCommunity.setName("Test Community");
        newCommunity.setOwnerUsername("Owner");

        ResponseEntity<Communities> createCommunityResponse = restTemplate.postForEntity(
                baseUrl + "/api/communities",
                newCommunity,
                Communities.class
        );

        Communities createdCommunity = createCommunityResponse.getBody();
        assertNotNull(createdCommunity);
        UUID communityId = createdCommunity.getId();

        createTestUser("User1");
        createTestUser("User2");
        createTestUser("User3");

        addCommunityMember(communityId, "User1");
        addCommunityMember(communityId, "User2");
        addCommunityMember(communityId, "User3");

        ResponseEntity<PagedResponse<Users>> firstPageResponse = restTemplate.exchange(
                baseUrl + "/api/communities/" + communityId + "/members",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PagedResponse<Users>>() {},
                1, 10);

        assertEquals(HttpStatus.OK, firstPageResponse.getStatusCode());

        PagedResponse<Users> firstPage = firstPageResponse.getBody();
        assertNotNull(firstPage);

        assertTrue(firstPage.isFirst());
        assertTrue(firstPage.isLast());
        assertEquals(1, firstPage.getCurrentPage());
        assertEquals(10, firstPage.getPageSize());
        assertEquals(3, firstPage.getTotalElements());
        assertEquals(3, firstPage.getContent().size());

        ResponseEntity<PagedResponse<Users>> secondPageResponse = restTemplate.exchange(
                baseUrl + "/api/communities/" + communityId + "/members?page=2&size=2",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<PagedResponse<Users>>() {});

        assertEquals(HttpStatus.OK, secondPageResponse.getStatusCode());

        PagedResponse<Users> secondPage = secondPageResponse.getBody();
        assertNotNull(secondPage);

        assertFalse(secondPage.isFirst());
        assertFalse(secondPage.isLast());
        assertEquals(2, secondPage.getCurrentPage());
        assertEquals(2, secondPage.getPageSize());
        assertEquals(3, secondPage.getTotalElements());
        assertEquals(1, secondPage.getContent().size());
    }

    private void createTestUser(String username) {
        Users newUser = new Users();
        newUser.setUserName(username);

        restTemplate.postForEntity(baseUrl + "/api/users", newUser, Users.class);
    }

    private void addCommunityMember(UUID communityId, String username) {
        restTemplate.postForEntity(
                baseUrl + "/api/communities/" + communityId + "/members/" + username,
                null,
                Users.class
        );
    }
}
