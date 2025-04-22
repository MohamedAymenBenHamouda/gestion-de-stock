package com.pfa.gestionstock.repository;

import com.pfa.gestionstock.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findByEntrepotId(Long entrepotId);

    // 🔍 Trouver un stock dans un entrepôt pour un produit donné
    Optional<Stock> findByProduitIdAndEntrepotId(Long produitId, Long entrepotId);

    // 🔍 Trouver un stock dans un point de vente pour un produit donné
    Optional<Stock> findByProduitIdAndPointDeVenteId(Long produitId, Long pointDeVenteId);
}
