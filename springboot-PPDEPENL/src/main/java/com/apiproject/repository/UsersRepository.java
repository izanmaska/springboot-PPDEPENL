package com.apiproject.repository;
import com.apiproject.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository<Users, Long> {
}
