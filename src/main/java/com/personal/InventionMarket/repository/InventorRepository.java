package com.personal.InventionMarket.repository;

import com.personal.InventionMarket.model.Inventor;
import com.personal.InventionMarket.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventorRepository extends JpaRepository<Inventor, String>, UserRepository<Inventor>{
}
