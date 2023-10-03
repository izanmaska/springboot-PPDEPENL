package com.MDL30MBDEPENL.controller;

import com.MDL30MBDEPENL.model.Communities;
import com.MDL30MBDEPENL.model.Users;
import com.MDL30MBDEPENL.service.CommunitiesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class CommunitiesController {
    @Autowired
    private CommunitiesService communitiesService;

//    @PostMapping
//    private ResponseEntity<Communities> saveCommunity (@RequestBody Communities communities){
//        Communities temp = communitiesService.createCommunity(communities);
//        try {
//            return ResponseEntity.created(new URI("api/users/"+temp.getId())).body(temp);
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }
//    @GetMapping
//    private ResponseEntity<List<Communities>> listAllCommunities (){
//        return ResponseEntity.ok(communitiesService.communitiesFindAll());
//    }
//
//    @DeleteMapping
//    private ResponseEntity<Void> deleteCommunity (@RequestBody Communities communities){
//        communitiesService.deleteCommunity(communities);
//        return ResponseEntity.ok().build();
//    }
//    @GetMapping (value = "{id}")
//    private ResponseEntity<Optional<Communities>> findUserById (@PathVariable ("id") Long id){
//        return ResponseEntity.ok(communitiesService.communityFindById(id));
//    }

}
