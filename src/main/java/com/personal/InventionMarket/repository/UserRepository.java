package com.personal.InventionMarket.repository;

import com.personal.InventionMarket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository<T extends User> {
    public Optional<T> findByUsername(String username);
    public Optional<T> findByEmail(String email);
    public Optional<T> findByPhone(String phone);
}
