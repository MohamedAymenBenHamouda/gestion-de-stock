package com.pfa.gestionstock.dto;

public class TransfertRequest {

    private Long produitId; // ID du produit
    private Long entrepotId; // ID de l'entrepôt source
    private Long pointDeVenteId; // ID du point de vente destination
    private int quantite; // Quantité à transférer

    // Getters et Setters
    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public Long getEntrepotId() {
        return entrepotId;
    }

    public void setEntrepotId(Long entrepotId) {
        this.entrepotId = entrepotId;
    }

    public Long getPointDeVenteId() {
        return pointDeVenteId;
    }

    public void setPointDeVenteId(Long pointDeVenteId) {
        this.pointDeVenteId = pointDeVenteId;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
