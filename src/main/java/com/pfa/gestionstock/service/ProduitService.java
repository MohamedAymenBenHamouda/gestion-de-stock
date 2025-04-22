package com.pfa.gestionstock.service;

import com.pfa.gestionstock.entities.Produit;
import com.pfa.gestionstock.repository.EntrepotRepository;
import com.pfa.gestionstock.repository.ProduitRepository;
/*import com.pfa.gestionstock.web.controller.ProduitRequest;*/

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class ProduitService {
    

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private EntrepotRepository EntrepotRepository;

    @Transactional

    // Créer un produit
    public Produit createProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    // Récupérer tous les produits
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    // Récupérer un produit par ID
    public Optional<Produit> getProduitById(Long id) {
        return produitRepository.findById(id);
    }

    // Mettre à jour un produit
    public Produit updateProduit(Long id, Produit produit) {
        produit.setId(id); // Assurez-vous que l'ID est correct
        return produitRepository.save(produit);
    }

    // Supprimer un produit
    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }


    




    /*  Récupérer des produits par catégorie
    public List<Produit> getProduitsByCategorie(String categorie) {
        return produitRepository.findByCategorie(categorie);
    }*/
}