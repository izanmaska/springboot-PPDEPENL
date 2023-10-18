package com.ethan.apiproject.IntegrationTests.RepositoryIntegrationTest;

import com.ethan.apiproject.model.Communities;
import com.ethan.apiproject.repository.CommunitiesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CommunitiesRIT {

    @Autowired
    private CommunitiesRepository communitiesRepository;

    @Test
    public void testSaveAndFindCommunity() {
        Communities community = new Communities();
        community.setName("Test Community");
        community.setOwnerUsername("Owner");

        Communities savedCommunity = communitiesRepository.save(community);
        assertNotNull(savedCommunity.getId());

        Communities foundCommunity = communitiesRepository.findById(savedCommunity.getId()).orElse(null);
        assertNotNull(foundCommunity);
        assertEquals("Test Community", foundCommunity.getName());
        assertEquals("Owner", foundCommunity.getOwnerUsername());
    }

    @Test
    public void testFindAllCommunities() {
        List<Communities> communities = communitiesRepository.findAll();
        assertNotNull(communities);
        assertTrue(communities.size() > 0);
    }

    @Test
    public void testDeleteCommunity() {
        Communities community = new Communities();
        community.setName("Test Community");
        community.setOwnerUsername("A test community");

        Communities savedCommunity = communitiesRepository.save(community);
        assertNotNull(savedCommunity.getId());

        communitiesRepository.deleteById(savedCommunity.getId());
        assertFalse(communitiesRepository.findById(savedCommunity.getId()).isPresent());
    }

}
