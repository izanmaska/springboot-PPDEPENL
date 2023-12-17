package com.ethan.apiproject.service;

import com.ethan.apiproject.model.Communities;
import com.ethan.apiproject.model.User;
import com.ethan.apiproject.repository.CommunitiesRepository;

import com.ethan.apiproject.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class CommunitiesService {
    @Autowired
    private CommunitiesRepository communitiesRepository;
    @Autowired
    private UsersRepository usersRepository;

    public Communities createCommunity(Communities communities){
        return communitiesRepository.save(communities);
    }
    public Page<Communities> listAllCommunities(Pageable pageable) {
        return communitiesRepository.findAll(pageable);
    }

    public void deleteCommunity(Communities communities){

        communitiesRepository.delete(communities);
    }


    public Optional<Communities> communityFindById(UUID id){

        return communitiesRepository.findCommunityById(id);
    }
    public List<Communities> findCommunitiesByOwnerId(UUID ownerId) {
        return communitiesRepository.findByOwnerId(ownerId);
    }
    public Communities addUserToCommunity(UUID communityId, String userId) {
        Communities community = communitiesRepository.findCommunityById(communityId).orElse(null);
        User user = usersRepository.findById(userId).orElse(null);

        if (community != null && user != null) {
            community.getUsers().add(user);
            return communitiesRepository.save(community);
        }

        return null;
    }
    public Communities updateCommunity(Communities community) {
        return communitiesRepository.save(community);
    }

    public Communities removeUserFromCommunity(UUID communityId, String userId) {
        Communities community = communitiesRepository.findCommunityById(communityId).orElse(null);
        User user = usersRepository.findById(userId).orElse(null);

        if (community != null && user != null) {
            community.getUsers().remove(user);
            return communitiesRepository.save(community);
        }

        return null;
    }


}
