package com.ethan.apiproject.controller;

import com.ethan.apiproject.model.Communities;
import com.ethan.apiproject.service.CommunitiesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/communities")
public class CommunitiesController {
    @Autowired
    private CommunitiesService communitiesService;

    @PostMapping
    private ResponseEntity<Communities> saveCommunity (@RequestBody Communities communities){
        Communities temp = communitiesService.createCommunity(communities);
        try {
            return ResponseEntity.created(new URI("communities/"+temp.getId())).body(temp);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping("/communities")
    private ResponseEntity<List<Communities>> listAllCommunities (){
        return ResponseEntity.ok(communitiesService.communitiesFindAll());
    }

    @DeleteMapping
    private ResponseEntity<Void> deleteCommunity (@RequestBody Communities communities){
        communitiesService.deleteCommunity(communities);
        return ResponseEntity.ok().build();
    }
    @GetMapping ("/{id}")
    private ResponseEntity<Optional<Communities>> findUserById (@PathVariable ("id") Long id){
        return ResponseEntity.ok(communitiesService.communityFindById(id));
    }
    @GetMapping ("/owner/{id}")
    public ResponseEntity<List<Communities>> findCommunitiesByOwnerId(@PathVariable Long ownerId) {
        List<Communities> communities = communitiesService.findCommunitiesByOwnerId(ownerId);
        return ResponseEntity.ok(communities);
    }
    @PostMapping("/{communityId}/addUser/{userId}")
    public ResponseEntity<Communities> addUserToCommunity(@PathVariable Long communityId, @PathVariable Long userId) {
        Communities updatedCommunity = communitiesService.addUserToCommunity(communityId, userId);

        if (updatedCommunity != null) {
            return ResponseEntity.ok(updatedCommunity);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/{communityId}/removeUser/{userId}")
    public ResponseEntity<Communities> removeUserFromCommunity(@PathVariable Long communityId, @PathVariable Long userId) {
        Communities updatedCommunity = communitiesService.removeUserFromCommunity(communityId, userId);

        if (updatedCommunity != null) {
            return ResponseEntity.ok(updatedCommunity);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
