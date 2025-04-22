package com.pfa.gestionstock.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "stocks",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"produit_id", "entrepot_id"}),
        @UniqueConstraint(columnNames = {"produit_id", "point_de_vente_id"})
    }
)

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"produit", "entrepot", "pointDeVente"})
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
    @JoinColumn(name = "entrepot_id", nullable = true) // Nullable car un stock peut être dans un point de vente
    @JsonIgnoreProperties("stocks") // Évite la boucle infinie avec Entrepot
    private Entrepot entrepot;

    @ManyToOne
    @JoinColumn(name = "point_de_vente_id", nullable = true) // Nullable car un stock peut être dans un entrepôt
    @JsonIgnoreProperties("stocks") // Évite la boucle infinie avec PointDeVente
    private PointDeVente pointDeVente;

    @Column(nullable = false)
    private int quantite;

    @Column(nullable = false)
    private String nom;

    // Nouveau champ pour le type de lieu
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private TypeLieu typeLieu;

    // Vérification avant insertion ou mise à jour
    @PrePersist
    @PreUpdate
    private void verifierQuantite() {
        if (this.quantite < 0) {
            throw new IllegalArgumentException("La quantité ne peut pas être négative");
        }
    }

    // Enum représentant les types de lieux
    public enum TypeLieu {
        ENTREPOT, POINT_DE_VENTE;
    }
}
