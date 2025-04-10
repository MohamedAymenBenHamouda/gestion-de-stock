package com.pfa.gestionstock.web.controller;

import com.pfa.gestionstock.entities.Stock;
import com.pfa.gestionstock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {@Autowired
    private StockService stockService;

    @PostMapping
    public Stock ajouterStock(@RequestBody Stock stock) {
        return stockService.ajouterStock(stock);
    }

    @GetMapping
    public List<Stock> getAllStocks() {
        return stockService.getAllStocks();
    }

    @PutMapping("/{id}")
    public Stock mettreAJourStock(@PathVariable Long id, @RequestParam int quantite) {
        return stockService.mettreAJourStock(id, quantite);
    }

    @GetMapping("/entrepot/{entrepotId}")
    public List<Stock> obtenirStocksParEntrepot(@PathVariable Long entrepotId) {
        return stockService.obtenirStocksParEntrepot(entrepotId);
    }

    @DeleteMapping("/{id}")
    public void supprimerStock(@PathVariable Long id) {
        stockService.supprimerStock(id);
    }
}
