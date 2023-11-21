package com.ethan.apiproject.repository;

import com.ethan.apiproject.model.Communities;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface CommunitiesRepository extends JpaRepository<Communities, Long> {
    List<Communities> findByOwnerId(UUID ownerId);
    Page<Communities> findAll(Pageable pageable);


    Optional<Communities> findCommunityById(UUID id);
}
