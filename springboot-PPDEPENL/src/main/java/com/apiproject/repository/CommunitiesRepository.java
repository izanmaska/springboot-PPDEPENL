package com.apiproject.repository;

import com.apiproject.model.Communities;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommunitiesRepository extends JpaRepository<Communities, Long> {
}
