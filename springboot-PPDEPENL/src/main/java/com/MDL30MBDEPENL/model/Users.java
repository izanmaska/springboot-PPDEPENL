package com.MDL30MBDEPENL.model;

import java.time.LocalDateTime;
import java.util.List;

public record Users(
        Long id,
        String userName,
        String email,
        Status status,
        Type userType,
        LocalDateTime dateCreated,
        LocalDateTime dateUpdated,
        String url
) {


}
