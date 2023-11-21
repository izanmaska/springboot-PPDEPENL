package com.ethan.apiproject.repository;

import com.ethan.apiproject.model.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceEntityRepository extends JpaRepository<ServiceEntity, Long> {
}
