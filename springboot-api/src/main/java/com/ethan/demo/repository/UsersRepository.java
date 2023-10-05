package com.ethan.demo.repository;
import com.ethan.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;



public interface UsersRepository extends JpaRepository<Users, Long> {
}
