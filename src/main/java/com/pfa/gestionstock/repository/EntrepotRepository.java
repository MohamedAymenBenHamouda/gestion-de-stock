package com.pfa.gestionstock.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfa.gestionstock.entities.Entrepot;

import org.springframework.lang.NonNull;

@Repository
public interface EntrepotRepository extends JpaRepository<Entrepot, Long> {
    // Méthodes personnalisées optionnelles :
    
    // Trouver un entrepôt par son nom (exemple)
    Entrepot findByNom(String nom);
    
    // Vérifier si un entrepôt existe par son ID
    boolean existsById(@NonNull Long id);
}