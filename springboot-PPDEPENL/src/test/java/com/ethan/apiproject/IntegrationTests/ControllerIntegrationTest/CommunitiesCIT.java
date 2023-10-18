package com.ethan.apiproject.IntegrationTests.ControllerIntegrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommunitiesCIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private final String baseUrl = "http://localhost:";

    @Test
    public void testCreateCommunity() {
        com.ethan.apiproject.model.Communities newCommunity = new com.ethan.apiproject.model.Communities();
        newCommunity.setName("Test Community");
        newCommunity.setOwnerUsername("Owner");

        ResponseEntity<com.ethan.apiproject.model.Communities> response = restTemplate.postForEntity(
                baseUrl + port + "/api/communities",
                newCommunity,
                com.ethan.apiproject.model.Communities.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        com.ethan.apiproject.model.Communities createdCommunity = response.getBody();
        assertNotNull(createdCommunity);
        assertNotNull(createdCommunity.getId());
        assertEquals("Test Community", createdCommunity.getName());
        assertEquals("Owner", createdCommunity.getOwnerUsername());
    }

    @Test
    public void testListAllCommunities() {
        ResponseEntity<com.ethan.apiproject.model.Communities[]> response = restTemplate.getForEntity(
                baseUrl + port + "/api/communities",
                com.ethan.apiproject.model.Communities[].class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        com.ethan.apiproject.model.Communities[] communities = response.getBody();
        assertNotNull(communities);
        assertEquals(1, communities.length);

        for (com.ethan.apiproject.model.Communities community : communities) {
            assertNotNull(community.getName());
            assertNotNull(community.getOwnerUsername());
        }
    }

    @Test
    public void testDeleteCommunity() {
        com.ethan.apiproject.model.Communities newCommunity = new com.ethan.apiproject.model.Communities();
        newCommunity.setName("Test Community");
        newCommunity.setOwnerUsername("Owner");

        ResponseEntity<com.ethan.apiproject.model.Communities> createResponse = restTemplate.postForEntity(
                baseUrl + port + "/api/communities",
                newCommunity,
                com.ethan.apiproject.model.Communities.class
        );

        assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());
        com.ethan.apiproject.model.Communities createdCommunity = createResponse.getBody();

        assert createdCommunity != null;

        restTemplate.delete(baseUrl + port + "/api/communities/" + createdCommunity.getId());

        ResponseEntity<com.ethan.apiproject.model.Communities> getResponse = restTemplate.getForEntity(
                baseUrl + port + "/api/communities/" + createdCommunity.getId(),
                com.ethan.apiproject.model.Communities.class
        );

        assertEquals(HttpStatus.NOT_FOUND, getResponse.getStatusCode());
        assertNull(getResponse.getBody());
    }
}
