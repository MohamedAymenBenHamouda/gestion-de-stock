package com.pfa.gestionstock.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class TransfertStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Produit produit;

    @ManyToOne
    private Entrepot entrepotSource;

    @ManyToOne
    private PointDeVente pointDeVenteDestination;

    private int quantite;

    private LocalDateTime dateTransfert;

    // Constructeur sans argument (requis par JPA)
    public TransfertStock() {
        this.dateTransfert = LocalDateTime.now(); // Date actuelle du transfert
    }

    // Constructeur avec tous les paramètres
    public TransfertStock(Produit produit, Entrepot entrepotSource, PointDeVente pointDeVenteDestination, int quantite) {
        this.produit = produit;
        this.entrepotSource = entrepotSource;
        this.pointDeVenteDestination = pointDeVenteDestination;
        this.quantite = quantite;
        this.dateTransfert = LocalDateTime.now(); // Date actuelle du transfert
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

    public Entrepot getEntrepotSource() {
        return entrepotSource;
    }

    public void setEntrepotSource(Entrepot entrepotSource) {
        this.entrepotSource = entrepotSource;
    }

    public PointDeVente getPointDeVenteDestination() {
        return pointDeVenteDestination;
    }

    public void setPointDeVenteDestination(PointDeVente pointDeVenteDestination) {
        this.pointDeVenteDestination = pointDeVenteDestination;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public LocalDateTime getDateTransfert() {
        return dateTransfert;
    }

    public void setDateTransfert(LocalDateTime dateTransfert) {
        this.dateTransfert = dateTransfert;
    }
}
