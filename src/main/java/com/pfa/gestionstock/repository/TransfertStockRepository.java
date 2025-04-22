package com.pfa.gestionstock.repository;

import com.pfa.gestionstock.entities.TransfertStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransfertStockRepository extends JpaRepository<TransfertStock, Long> {
    // Cette interface hérite de JpaRepository, donc elle a déjà les méthodes de base comme save(), findById(), etc.
}
