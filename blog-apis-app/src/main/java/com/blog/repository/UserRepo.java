package com.blog.repository;

import java.util.Optional;

import com.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {


    Optional<User> findByEmail(String email);
}
