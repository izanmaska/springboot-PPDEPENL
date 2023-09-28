package com.MDL30MBDEPENL.repository;

import com.MDL30MBDEPENL.model.Communities;
import com.MDL30MBDEPENL.model.Users;
import com.MDL30MBDEPENL.model.Status;
import com.MDL30MBDEPENL.model.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class usersCommunitiesAPI {
    private final List<Users> usersList = new ArrayList<>();
    public usersCommunitiesAPI(){

    }
    public List<Users> usersFindAll(){
        return usersList;
    }

    public Optional<Users> usersFindById(Integer id){
        return usersList.stream().filter(c -> c.id().equals(id)).findFirst();
    }


    public void saveUser(Users users){
        usersList.removeIf(c->c.id().equals(users.id()));
        usersList.add(users);
    }
    @PostConstruct
    private void initUser(){
        Users c = new Users(1,
                "test",
                "test",
                Status.IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                "")
                ;
        usersList.add(c);
    }

    public boolean existsById(Integer id){
        return usersList.stream().filter(c -> c.id().equals(id)).count() == 1;
    }
    public void delete(Integer id){
        usersList.removeIf(c->c.id().equals(id));

    }
    private final List<Communities> communitiesList = new ArrayList<>();
    public List<Communities> findAll(){
        return communitiesList;
    }

    public Optional<Communities> findById(Integer id){
        return communitiesList.stream().filter(c -> c.id().equals(id)).findFirst();
    }


    public void saveCommunity(Communities communities){
        communitiesList.removeIf(c->c.id().equals(communities.id()));
        communitiesList.add(communities);
    }
    @PostConstruct
    private void initCom(){
        Communities c = new Communities(1,
                "test",
                "test",
                Status.IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                "")
                ;
        communitiesList.add(c);
    }

    public boolean communityExistsById(Integer id){
        return communitiesList.stream().filter(c -> c.id().equals(id)).count() == 1;
    }
    public void communityDelete(Integer id){
        communitiesList.removeIf(c->c.id().equals(id));

    }
}
