package com.pfa.gestionstock.service;

import com.pfa.gestionstock.entities.Entrepot;
import com.pfa.gestionstock.entities.Produit;
import com.pfa.gestionstock.entities.Stock;
import com.pfa.gestionstock.repository.EntrepotRepository;
import com.pfa.gestionstock.repository.ProduitRepository;
import com.pfa.gestionstock.repository.StockRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class StockService {

    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private EntrepotRepository entrepotRepository;


    public List<Stock> getStocksByEntrepotId(Long entrepotId) {
        return stockRepository.findByEntrepotId(entrepotId);
    }
    public Stock createStock(Long entrepotId, Long produitId, int quantite) {
        Entrepot entrepot = entrepotRepository.findById(entrepotId)
                .orElseThrow(() -> new RuntimeException("Entrepôt non trouvé"));
        
        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé"));

        Stock stock = new Stock();
        stock.setEntrepot(entrepot);
        stock.setProduit(produit);
        stock.setQuantite(quantite);

        return stockRepository.save(stock);
    }
    public Stock updateStock(Long stockId, int nouvelleQuantite) {
        Stock stock = stockRepository.findById(stockId)
                .orElseThrow(() -> new RuntimeException("Stock non trouvé"));
        
        stock.setQuantite(nouvelleQuantite);
        return stockRepository.save(stock);
    }

}
