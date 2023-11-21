package com.ethan.apiproject.repository;

import com.ethan.apiproject.model.Role;
import com.ethan.apiproject.model.enums.UserRole;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class CustomRoleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<Role> findByName(UserRole name) {
        String jpql = "SELECT r FROM Role r WHERE r.name = :name";
        return entityManager.createQuery(jpql, Role.class)
                .setParameter("name", name)
                .getResultList()
                .stream()
                .findFirst();
    }
}
