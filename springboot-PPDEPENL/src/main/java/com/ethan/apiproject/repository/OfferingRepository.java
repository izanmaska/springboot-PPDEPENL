package com.ethan.apiproject.repository;


import com.ethan.apiproject.model.offerings.Offering;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface OfferingRepository extends JpaRepository<Offering, UUID> {
}
