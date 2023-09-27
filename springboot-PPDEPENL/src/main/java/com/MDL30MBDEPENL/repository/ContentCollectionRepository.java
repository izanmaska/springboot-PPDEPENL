package com.MDL30MBDEPENL.repository;

import com.MDL30MBDEPENL.model.Content;
import com.MDL30MBDEPENL.model.Status;
import com.MDL30MBDEPENL.model.Type;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ContentCollectionRepository {
    private final List<Content> contentList = new ArrayList<>();
    public ContentCollectionRepository(){

    }
    public List<Content> findAll(){
        return contentList;
    }

    public Optional<Content> findById(Integer id){
        return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
    }


    public void save(Content content){
        contentList.removeIf(c->c.id().equals(content.id()));
        contentList.add(content);
    }
    @PostConstruct
    private void init(){
        Content c = new Content(1,
                "test",
                "test",
                Status.IDEA,
                Type.ARTICLE,
                LocalDateTime.now(),
                null,
                "")
                ;
        contentList.add(c);
    }

    public boolean existsById(Integer id){
        return contentList.stream().filter(c -> c.id().equals(id)).count() == 1;
    }
    public void delete(Integer id){
        contentList.removeIf(c->c.id().equals(id));

    }
}
