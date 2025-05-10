package com.pfa.gestionstock.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pfa.gestionstock.entities.Utilisateur;
import com.pfa.gestionstock.enums.Role;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByAdresseEmail(String adresseEmail);
    List<Utilisateur> findByRole(Role role);
}
