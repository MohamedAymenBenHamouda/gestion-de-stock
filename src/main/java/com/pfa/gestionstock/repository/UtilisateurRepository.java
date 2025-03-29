package com.pfa.gestionstock.repository;

import com.pfa.gestionstock.entities.Utilisateur;
import com.pfa.gestionstock.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    // Trouver un utilisateur par son adresse email
    Optional<Utilisateur> findByAdresseEmail(String adresseEmail);

    // Trouver tous les utilisateurs par rôle
    List<Utilisateur> findByRole(Role role);
}