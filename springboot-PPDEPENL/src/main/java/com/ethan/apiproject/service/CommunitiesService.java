package com.ethan.apiproject.service;

import com.ethan.apiproject.model.Communities;
import com.ethan.apiproject.model.Users;
import com.ethan.apiproject.repository.CommunitiesRepository;

import com.ethan.apiproject.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public class CommunitiesService {
    @Autowired
    private CommunitiesRepository communitiesRepository;
    @Autowired
    private UsersRepository usersRepository;

    public Communities createCommunity(Communities communities){
        return communitiesRepository.save(communities);
    }
    public List<Communities> communitiesFindAll(){

        return communitiesRepository.findAll();
    }
    public void deleteCommunity(Communities communities){

        communitiesRepository.delete(communities);
    }


    public Optional<Communities> communityFindById(Long id){

        return communitiesRepository.findById(id);
    }
    public List<Communities> findCommunitiesByOwnerId(Long ownerId) {
        return communitiesRepository.findByOwnerId(ownerId);
    }
    public Communities addUserToCommunity(Long communityId, Long userId) {
        Communities community = communitiesRepository.findById(communityId).orElse(null);
        Users user = usersRepository.findById(userId).orElse(null);

        if (community != null && user != null) {
            community.getUsers().add(user);
            return communitiesRepository.save(community);
        }

        return null;
    }
    public Communities updateCommunity(Communities community) {
        return communitiesRepository.save(community);
    }

    public Communities removeUserFromCommunity(Long communityId, Long userId) {
        Communities community = communitiesRepository.findById(communityId).orElse(null);
        Users user = usersRepository.findById(userId).orElse(null);

        if (community != null && user != null) {
            community.getUsers().remove(user);
            return communitiesRepository.save(community);
        }

        return null;
    }


}
