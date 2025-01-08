package com.example.car_dealership.repository;

import com.example.car_dealership.model.InternalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<InternalUser, Integer> {
    Optional<InternalUser> findByUsername(String username);
}