package com.MDL30MBDEPENL.repository;
import com.MDL30MBDEPENL.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;



public interface UsersRepository extends JpaRepository<Users, Long> {
}
