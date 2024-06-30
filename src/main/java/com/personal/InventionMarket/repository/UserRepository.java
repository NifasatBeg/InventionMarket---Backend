package com.personal.InventionMarket.repository;

import com.personal.InventionMarket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    public Optional<User> findByUsername(String username);
    public Optional<User> findByEmail(String email);
    public Optional<User> findByPhone(String phone);
}
