package com.MDL30MBDEPENL.model;

import java.time.LocalDateTime;
import java.util.List;

public record Communities(Long id,
                          String name,
                          String desc,
                          Long ownerId,
                          String ownerUsername,
                          Status status,
                          LocalDateTime dateCreated,
                          LocalDateTime dateUpdated,
                          String url,
                          List<Users> users) {
    public List<Users> getUsers() {
        return users;
    }
}
