package com.pfa.gestionstock.service;

import com.pfa.gestionstock.entities.Stock;

import com.pfa.gestionstock.repository.StockRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public Stock ajouterStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public Stock mettreAJourStock(Long id, int nouvelleQuantite) {
        Stock stock = stockRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Stock non trouvé"));
        stock.setQuantite(nouvelleQuantite);
        return stockRepository.save(stock);
    }

    public List<Stock> obtenirStocksParEntrepot(Long entrepotId) {
        return stockRepository.findByEntrepotId(entrepotId);
    }

    public void supprimerStock(Long id) {
        stockRepository.deleteById(id);
    }
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }
    
}