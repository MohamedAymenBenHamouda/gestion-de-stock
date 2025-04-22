package com.pfa.gestionstock.service;

import com.pfa.gestionstock.entities.Entrepot;
import com.pfa.gestionstock.entities.PointDeVente;
import com.pfa.gestionstock.entities.Produit;
import com.pfa.gestionstock.entities.Stock;
import com.pfa.gestionstock.entities.TransfertStock;
import com.pfa.gestionstock.repository.EntrepotRepository;
import com.pfa.gestionstock.repository.PointDeVenteRepository;
import com.pfa.gestionstock.repository.ProduitRepository;
import com.pfa.gestionstock.repository.StockRepository;
import com.pfa.gestionstock.repository.TransfertStockRepository;
import com.pfa.gestionstock.dto.TransfertRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransfertStockService {

    private final StockRepository stockRepository;
    private final TransfertStockRepository transfertStockRepository;
    private final ProduitRepository produitRepository;
    private final EntrepotRepository entrepotRepository;
    private final PointDeVenteRepository pointDeVenteRepository;

    public void transfertStock(TransfertRequest transfertRequest) {
        // 1. Chercher le stock du produit dans l'entrepôt
        Stock stockEntrepot = stockRepository.findByProduitIdAndEntrepotId(
                transfertRequest.getProduitId(), 
                transfertRequest.getEntrepotId()
        ).orElseThrow(() -> new RuntimeException("Stock introuvable dans l'entrepôt"));

        if (stockEntrepot.getQuantite() < transfertRequest.getQuantite()) {
            throw new RuntimeException("Quantité insuffisante dans l'entrepôt");
        }

        // 2. Décrémenter le stock de l'entrepôt
        stockEntrepot.setQuantite(stockEntrepot.getQuantite() - transfertRequest.getQuantite());
        stockRepository.save(stockEntrepot);  // Sauvegarder la mise à jour de l'entrepôt

        // 3. Chercher ou créer le stock du produit dans le point de vente
        Stock stockPointDeVente = stockRepository.findByProduitIdAndPointDeVenteId(
                transfertRequest.getProduitId(), 
                transfertRequest.getPointDeVenteId()
        ).orElseGet(() -> {
            // Récupérer produit et point de vente
            Produit produit = produitRepository.findById(transfertRequest.getProduitId())
                    .orElseThrow(() -> new RuntimeException("Produit non trouvé"));
            PointDeVente pointDeVente = pointDeVenteRepository.findById(transfertRequest.getPointDeVenteId())
                    .orElseThrow(() -> new RuntimeException("Point de vente non trouvé"));

            // Créer un nouveau Stock pour le point de vente
            Stock nouveauStock = new Stock();
            nouveauStock.setProduit(produit);
            nouveauStock.setEntrepot(null); // Pas d'entrepôt pour un stock de point de vente
            nouveauStock.setPointDeVente(pointDeVente);
            nouveauStock.setQuantite(0); // Initialiser à 0 pour ajouter la quantité
            nouveauStock.setNom(produit.getNom());
            nouveauStock.setTypeLieu(Stock.TypeLieu.POINT_DE_VENTE);

            return nouveauStock;
        });

        // 4. Ajouter la quantité au stock du point de vente
        stockPointDeVente.setQuantite(stockPointDeVente.getQuantite() + transfertRequest.getQuantite());
        stockRepository.save(stockPointDeVente);  // Sauvegarder le stock du point de vente

        // 5. Enregistrer le transfert dans TransfertStock
        TransfertStock transfertStock = new TransfertStock(
                stockEntrepot.getProduit(),
                stockEntrepot.getEntrepot(),
                stockPointDeVente.getPointDeVente(),
                transfertRequest.getQuantite()
        );
        transfertStockRepository.save(transfertStock);
    }
}
