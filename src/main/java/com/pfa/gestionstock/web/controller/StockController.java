package com.pfa.gestionstock.web.controller;

import com.pfa.gestionstock.entities.Stock;
import com.pfa.gestionstock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    /**
     * Crée un nouveau stock pour un entrepôt et un produit
     * @param entrepotId ID de l'entrepôt
     * @param produitId ID du produit
     * @param quantite Quantité à stocker
     * @return Le stock créé (format JSON)
     */
    @PostMapping
    public ResponseEntity<Stock> createStock(
            @RequestParam Long entrepotId,
            @RequestParam Long produitId,
            @RequestParam int quantite) {
        
        Stock createdStock = stockService.createStock(entrepotId, produitId, quantite);
        return ResponseEntity.ok(createdStock);
    }

    /**
     * Récupère tous les stocks d'un entrepôt
     * @param entrepotId ID de l'entrepôt
     * @return Liste des stocks (format JSON)
     */
    @GetMapping("/entrepot/{entrepotId}")
    public ResponseEntity<List<Stock>> getStocksByEntrepot(
            @PathVariable Long entrepotId) {
        
        List<Stock> stocks = stockService.getStocksByEntrepotId(entrepotId);
        return ResponseEntity.ok(stocks);
    }

    /**
     * Met à jour la quantité d'un stock existant
     * @param stockId ID du stock
     * @param newQuantite Nouvelle quantité
     * @return Stock mis à jour (format JSON)
     */
    @PutMapping("/{stockId}")
    public ResponseEntity<Stock> updateStockQuantity(
            @PathVariable Long stockId,
            @RequestParam int newQuantite) {
        
        Stock updatedStock = stockService.updateStock(stockId, newQuantite);
        return ResponseEntity.ok(updatedStock);
    }
}