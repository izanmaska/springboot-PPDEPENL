package com.MDL30MBDEPENL.model;

import java.time.LocalDateTime;

public record Transactions(Long id,
                           Long user1Id,
                           String user1Type,
                           Long user2Id,
                           String user2Type,
                           LocalDateTime date,
                           String url) {


}
