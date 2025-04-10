/*package com.pfa.gestionstock.web.controller;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.*;

public class ProduitRequest {
    @NotBlank(message = "Le nom du produit est obligatoire")
    @Size(max = 100, message = "Le nom ne peut pas dépasser 100 caractères")
    private String nom;

    @NotBlank(message = "La catégorie est obligatoire")
    @Size(max = 50, message = "La catégorie ne peut pas dépasser 50 caractères")
    private String categorie;

    @NotNull(message = "Le prix est obligatoire")
    @Positive(message = "Le prix doit être positif")
    @DecimalMin(value = "0.01", message = "Le prix doit être supérieur à 0")
    private Double prix;

    private String image; // Optionnel

    @NotNull(message = "L'ID de l'entrepôt est obligatoire")
    @Positive(message = "L'ID de l'entrepôt doit être positif")
    private Long entrepotId;

    @NotNull(message = "La quantité est obligatoire")
    @Min(value = 0, message = "La quantité ne peut pas être négative")
    private Integer quantite;

    // Constructeurs
    public ProduitRequest() {
    }

    public ProduitRequest(String nom, String categorie, Double prix, String image, Long entrepotId, Integer quantite) {
        this.nom = nom;
        this.categorie = categorie;
        this.prix = prix;
        this.image = image;
        this.entrepotId = entrepotId;
        this.quantite = quantite;
    }

    // Getters et Setters
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

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getEntrepotId() {
        return entrepotId;
    }

    public void setEntrepotId(Long entrepotId) {
        this.entrepotId = entrepotId;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }
}*/