package com.ethan.apiproject.repository;
import com.ethan.apiproject.model.Users;
import com.ethan.apiproject.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface UsersRepository extends JpaRepository<Users, UUID> {
    Optional<Users> findByUserName(String userName);

}
