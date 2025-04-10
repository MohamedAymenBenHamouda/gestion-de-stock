package com.pfa.gestionstock.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "stocks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"produit", "entrepot"})
@EqualsAndHashCode
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    @JsonIgnoreProperties("stocks") // Évite la boucle infinie avec Produit
    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "entrepot_id", nullable = false)
    @JsonIgnoreProperties("stocks") // Évite la boucle infinie avec Entrepot
    private Entrepot entrepot;

    @Column(nullable = false)
    private int quantite;

    @Column(nullable = false)
    private String nom;

    // Vérification avant insertion ou mise à jour
    @PrePersist
    @PreUpdate
    private void verifierQuantite() {
        if (this.quantite < 0) {
            throw new IllegalArgumentException("La quantité ne peut pas être négative");
        }
    }
}
