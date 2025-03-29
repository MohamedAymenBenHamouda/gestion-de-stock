package com.pfa.gestionstock.repository;

import com.pfa.gestionstock.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findByEntrepotId(Long entrepotId); // Trouver les stocks par ID d'entrepôt
}