package com.ethan.apiproject.IntegrationTests.APIIntegrationTests;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ethan.apiproject.model.Communities;
import com.ethan.apiproject.service.CommunitiesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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
        ResponseEntity<Communities[]> response = restTemplate.getForEntity(
                baseUrl + ":" + port + "/api/communities",
                Communities[].class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Communities[] communities = response.getBody();
        assertNotNull(communities);
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
        com.ethan.apiproject.model.Communities newCommunity = new com.ethan.apiproject.model.Communities();
        newCommunity.setName("Test Community");
        newCommunity.setOwnerUsername("Owner");

        ResponseEntity<com.ethan.apiproject.model.Communities> createResponse = restTemplate.postForEntity(
                baseUrl + "/api/communities",
                newCommunity,
                com.ethan.apiproject.model.Communities.class
        );

        com.ethan.apiproject.model.Communities createdCommunity = createResponse.getBody();
        assert createdCommunity != null;
        Long communityId = createdCommunity.getId();

        newCommunity.setName("Updated Community Name");
        newCommunity.setOwnerUsername("Updated Owner");

        restTemplate.put(baseUrl + "/api/communities/" + communityId, newCommunity);

        ResponseEntity<com.ethan.apiproject.model.Communities> updatedResponse = restTemplate.getForEntity(
                baseUrl + "/api/communities/" + communityId,
                com.ethan.apiproject.model.Communities.class
        );

        assertEquals(HttpStatus.OK, updatedResponse.getStatusCode());
        com.ethan.apiproject.model.Communities updatedCommunity = updatedResponse.getBody();

        assertNotNull(updatedCommunity);
        assertEquals(communityId, updatedCommunity.getId());
        assertEquals("Updated Community Name", updatedCommunity.getName());
        assertEquals("Updated Owner", updatedCommunity.getOwnerUsername());
    }
    @Test
    public void testListCommunityMembers() {
        com.ethan.apiproject.model.Communities newCommunity = new com.ethan.apiproject.model.Communities();
        newCommunity.setName("Test Community");
        newCommunity.setOwnerUsername("Owner");

        ResponseEntity<com.ethan.apiproject.model.Communities> createResponse = restTemplate.postForEntity(
                baseUrl + "/api/communities",
                newCommunity,
                com.ethan.apiproject.model.Communities.class
        );

        com.ethan.apiproject.model.Communities createdCommunity = createResponse.getBody();
        assert createdCommunity != null;
        Long communityId = createdCommunity.getId();


        ResponseEntity<com.ethan.apiproject.model.Users[]> membersResponse = restTemplate.getForEntity(
                baseUrl + "/api/communities/" + communityId + "/members",
                com.ethan.apiproject.model.Users[].class
        );

        assertEquals(HttpStatus.OK, membersResponse.getStatusCode());
        com.ethan.apiproject.model.Users[] communityMembers = membersResponse.getBody();
        assertNotNull(communityMembers);
    }

}
