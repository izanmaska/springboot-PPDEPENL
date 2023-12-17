package com.ethan.apiproject.repository;

import com.ethan.apiproject.model.Role;
import com.ethan.apiproject.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Collection<Role>> findByName(UserRole name);
}
