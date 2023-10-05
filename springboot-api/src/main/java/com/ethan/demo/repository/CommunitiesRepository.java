package com.ethan.demo.repository;

import com.ethan.demo.model.Communities;
import com.ethan.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommunitiesRepository extends JpaRepository<Communities, Long> {
}
