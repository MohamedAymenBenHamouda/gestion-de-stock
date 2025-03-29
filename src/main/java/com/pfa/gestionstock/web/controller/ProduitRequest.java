package com.pfa.gestionstock.web.controller;

public class ProduitRequest {
    private Long id;  // Changé de int à Long pour cohérence avec setter
    private String nom;
    private String categorie;
    private double prix;
    private Long entrepotId;  // Correction du nom (minuscule)
    private int quantite;
    
    // Getters et Setters complets et corrects
    
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

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Long getEntrepotId() {  // Correction du retour (Long) et du nom
        return entrepotId;
    }

    public void setEntrepotId(Long entrepotId) {  // Ajout du setter manquant
        this.entrepotId = entrepotId;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}


