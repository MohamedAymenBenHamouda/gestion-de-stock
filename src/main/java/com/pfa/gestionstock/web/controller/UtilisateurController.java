package com.pfa.gestionstock.web.controller;

import com.pfa.gestionstock.entities.Utilisateur;
import com.pfa.gestionstock.enums.Role;
import com.pfa.gestionstock.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    // Créer un utilisateur
    @PostMapping
    public Utilisateur createUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.createUtilisateur(utilisateur);
    }

    // Récupérer tous les utilisateurs
    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    // Récupérer un utilisateur par ID
    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable Long id) {
        Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateurById(id);
        return utilisateur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Mettre à jour un utilisateur
    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        Utilisateur updatedUtilisateur = utilisateurService.updateUtilisateur(id, utilisateur);
        return updatedUtilisateur != null ? ResponseEntity.ok(updatedUtilisateur) : ResponseEntity.notFound().build();
    }

    // Supprimer un utilisateur
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
        return ResponseEntity.noContent().build();
    }

    // Trouver un utilisateur par son adresse email
    @GetMapping("/email/{adresseEmail}")
    public ResponseEntity<Utilisateur> getUtilisateurByAdresseEmail(@PathVariable String adresseEmail) {
        Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateurByAdresseEmail(adresseEmail);
        return utilisateur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Trouver tous les utilisateurs par rôle
    @GetMapping("/role/{role}")
    public List<Utilisateur> getUtilisateursByRole(@PathVariable Role role) {
        return utilisateurService.getUtilisateursByRole(role);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Utilisateur loginRequest) {
        Optional<Utilisateur> utilisateurOpt = utilisateurService.login(loginRequest.getAdresseEmail(), loginRequest.getMotDePasse());
    
        if (utilisateurOpt.isPresent()) {
            return ResponseEntity.ok(utilisateurOpt.get());
        } else {
            return ResponseEntity.status(401).body("Email ou mot de passe incorrect");
        }
    }
    
    
}

