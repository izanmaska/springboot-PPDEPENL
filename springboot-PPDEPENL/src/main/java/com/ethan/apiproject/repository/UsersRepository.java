package com.ethan.apiproject.repository;
import com.ethan.apiproject.model.Users;
import com.ethan.apiproject.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository<Users, Long> {
}
