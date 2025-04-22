package com.pfa.gestionstock.web.controller;

import com.pfa.gestionstock.dto.TransfertRequest;
import com.pfa.gestionstock.entities.TransfertStock;
import com.pfa.gestionstock.repository.TransfertStockRepository;
import com.pfa.gestionstock.service.TransfertStockService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfert")
public class TransfertStockController {

    private final TransfertStockRepository transfertStockRepository;

    @Autowired
    private TransfertStockService transfertStockService;


    TransfertStockController(TransfertStockRepository transfertStockRepository) {
        this.transfertStockRepository = transfertStockRepository;
    }


    @GetMapping
    public List<TransfertStock> getAllTransferts() {
        return transfertStockRepository.findAll();
    }

    @PostMapping("/entrepot-to-pdv")
    public String transfertStock(@RequestParam Long produitId,
                                 @RequestParam Long entrepotId,
                                 @RequestParam Long pointDeVenteId,
                                 @RequestParam int quantite) {
        try {
            // Création d'un objet TransfertRequest
            TransfertRequest transfertRequest = new TransfertRequest();
            transfertRequest.setProduitId(produitId);
            transfertRequest.setEntrepotId(entrepotId);
            transfertRequest.setPointDeVenteId(pointDeVenteId);
            transfertRequest.setQuantite(quantite);

            // Appel du service avec le TransfertRequest
            transfertStockService.transfertStock(transfertRequest);
            return "Transfert effectué avec succès";
        } catch (Exception e) {
            return "Erreur : " + e.getMessage();
        }
    }
}
