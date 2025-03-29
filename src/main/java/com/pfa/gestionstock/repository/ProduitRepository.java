package com.pfa.gestionstock.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfa.gestionstock.entities.Produit;

@Repository
public interface ProduitRepository extends JpaRepository<Produit,   Long> {
    Produit findByNom(String nom);
    List<Produit> findByCategorie(String categorie);

}
