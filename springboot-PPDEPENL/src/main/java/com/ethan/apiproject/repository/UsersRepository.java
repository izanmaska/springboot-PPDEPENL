package com.ethan.apiproject.repository;
import com.ethan.apiproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface UsersRepository extends JpaRepository<User, String> {
    User save(User user);
    Optional<User> findByUserName(String userName);
    Optional<User> findUserById(String id);

}
