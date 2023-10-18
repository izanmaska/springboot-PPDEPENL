package com.ethan.apiproject.IntegrationTests.ServiceIntegrationTest;

import com.ethan.apiproject.service.CommunitiesService;
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
public class CommunitiesSIT {

    @LocalServerPort
    private int port;

    @Autowired
    private CommunitiesService communitiesService;

    private String baseUrl;
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        baseUrl = "http://localhost:" + port;
        restTemplate = new RestTemplate();
    }

    @Test
    public void testCreateCommunity() {
        com.ethan.apiproject.model.Communities newCommunity = new com.ethan.apiproject.model.Communities();
        newCommunity.setName("Test Community");
        newCommunity.setOwnerUsername("Owner");

        ResponseEntity<com.ethan.apiproject.model.Communities> response = restTemplate.postForEntity(
                baseUrl + "/api/communities",
                newCommunity,
                com.ethan.apiproject.model.Communities.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        com.ethan.apiproject.model.Communities createdCommunity = response.getBody();

        assertNotNull(createdCommunity);
        assertNotNull(createdCommunity.getId());
        assertEquals("Test Community", createdCommunity.getName());
        assertEquals("A test community", createdCommunity.getOwnerUsername());

    }

    @Test
    public void testListAllCommunities() {
        ResponseEntity<com.ethan.apiproject.model.Communities[]> response = restTemplate.getForEntity(
                baseUrl + "/api/communities",
                com.ethan.apiproject.model.Communities[].class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        com.ethan.apiproject.model.Communities[] communities = response.getBody();

        assertNotNull(communities);
    }

    @Test
    public void testFindCommunityById() {
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

        ResponseEntity<com.ethan.apiproject.model.Communities> findResponse = restTemplate.getForEntity(
                baseUrl + "/api/communities/" + communityId,
                com.ethan.apiproject.model.Communities.class
        );

        assertEquals(HttpStatus.OK, findResponse.getStatusCode());
        com.ethan.apiproject.model.Communities foundCommunity = findResponse.getBody();

        assertNotNull(foundCommunity);
        assertEquals(communityId, foundCommunity.getId());
        assertEquals("Test Community", foundCommunity.getName());
        assertEquals("Owner", foundCommunity.getOwnerUsername());

    }
}
