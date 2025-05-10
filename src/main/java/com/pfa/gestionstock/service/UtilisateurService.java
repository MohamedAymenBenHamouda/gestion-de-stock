package com.pfa.gestionstock.service;

import com.pfa.gestionstock.entities.Utilisateur;
import com.pfa.gestionstock.enums.Role;
import com.pfa.gestionstock.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    // Créer un utilisateur
    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    // Récupérer tous les utilisateurs
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    // Récupérer un utilisateur par ID
    public Optional<Utilisateur> getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id);
    }

    // Mettre à jour un utilisateur
    public Utilisateur updateUtilisateur(Long id, Utilisateur utilisateur) {
        utilisateur.setId(id); // Assurez-vous que l'ID est correct
        return utilisateurRepository.save(utilisateur);
    }

    // Supprimer un utilisateur
    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }

    // Trouver un utilisateur par son adresse email
    public Optional<Utilisateur> getUtilisateurByAdresseEmail(String adresseEmail) {
        return utilisateurRepository.findByAdresseEmail(adresseEmail);
    }

    // Trouver tous les utilisateurs par rôle
    public List<Utilisateur> getUtilisateursByRole(Role role) {
        return utilisateurRepository.findByRole(role);
    }
    public Optional<Utilisateur> login(String email, String motDePasse) {
        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findByAdresseEmail(email);
        if (utilisateurOpt.isPresent() && utilisateurOpt.get().getMotDePasse().equals(motDePasse)) {
            return utilisateurOpt;
        }
        return Optional.empty();
    }
    
}