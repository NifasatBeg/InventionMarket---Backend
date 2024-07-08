package com.personal.InventionMarket.repository;

import com.personal.InventionMarket.model.Invention;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventionRepository extends JpaRepository<Invention, String> {
}
