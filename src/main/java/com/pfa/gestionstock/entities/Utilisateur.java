package com.pfa.gestionstock.entities;

import com.pfa.gestionstock.enums.Role;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "utilisateurs")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false, unique = true, length = 100)
    private String adresseEmail;

    @Column(nullable = false, length = 100)
    private String motDePasse;

    @Enumerated(EnumType.STRING) // Stocke le rôle sous forme de chaîne de caractères
    @Column(nullable = false, length = 50)
    private Role role; // Utilise l'énumération Role

    // Constructeurs
    public Utilisateur() {}

    public Utilisateur(String nom, String adresseEmail, String motDePasse, Role role) {
        this.nom = nom;
        this.adresseEmail = adresseEmail;
        this.motDePasse = motDePasse;
        this.role = role;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresseEmail() {
        return adresseEmail;
    }

    public void setAdresseEmail(String adresseEmail) {
        this.adresseEmail = adresseEmail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    // Méthode toString (optionnelle)
    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", adresseEmail='" + adresseEmail + '\'' +
                ", role=" + role +
                '}';
    }

    // Méthodes equals et hashCode (optionnelles)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return Objects.equals(id, that.id) && Objects.equals(adresseEmail, that.adresseEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, adresseEmail);
    }
}