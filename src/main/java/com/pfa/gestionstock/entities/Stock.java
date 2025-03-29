package com.pfa.gestionstock.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)
    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "entrepot_id", nullable = false)
    private Entrepot entrepot;

    @Column(nullable = false)
    private int quantite;

    // Constructeurs
    public Stock() {}

    public Stock(Produit produit, Entrepot entrepot, int quantite) {
        this.produit = produit;
        this.entrepot = entrepot;
        this.quantite = quantite;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Entrepot getEntrepot() {
        return entrepot;
    }

    public void setEntrepot(Entrepot entrepot) {
        this.entrepot = entrepot;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    // Méthode toString (optionnelle)
    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", produit=" + produit +
                ", entrepot=" + entrepot +
                ", quantite=" + quantite +
                '}';
    }

    // Méthodes equals et hashCode (optionnelles)
    
}