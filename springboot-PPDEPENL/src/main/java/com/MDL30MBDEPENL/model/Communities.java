package com.MDL30MBDEPENL.model;

import java.time.LocalDateTime;

public record Communities(Integer id,
                          String title,
                          String desc,
                          Status status,
                          Type contentType,
                          LocalDateTime dateCreated,
                          LocalDateTime dateUpdated,
                          String url) {

}
