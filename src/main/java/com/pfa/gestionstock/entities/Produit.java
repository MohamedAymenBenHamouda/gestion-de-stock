package com.pfa.gestionstock.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "produits")
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = true, length = 50)
    private String categorie;

    @Column(nullable = false)
    private int quantite;

    @Column(nullable = false)
    private double prix;

    @Column(nullable = true) // L'image peut être optionnelle
    private String image;

    // Constructeurs
    public Produit() {}

    public Produit(String nom, String categorie, int quantite, double prix, String image) {
        this.nom = nom;
        this.categorie = categorie;
        this.quantite = quantite;
        this.prix = prix;
        this.image = image;
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

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
   /*  @OneToOne(mappedBy = "produit", cascade = CascadeType.ALL, orphanRemoval = true)
    private Stock stock; // Relation bidirectionnelle

    
    public void associateStock(Stock stock, Entrepot entrepot) {
        if (stock == null || entrepot == null) {
            throw new IllegalArgumentException("Stock et Entrepot ne peuvent pas être null");
        }
        this.stock = stock;
        stock.setProduit(this);
        stock.setEntrepot(entrepot);
    }*/
}