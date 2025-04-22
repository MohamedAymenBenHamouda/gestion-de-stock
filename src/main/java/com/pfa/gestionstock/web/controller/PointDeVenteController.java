package com.pfa.gestionstock.web.controller;

import com.pfa.gestionstock.entities.PointDeVente;
import com.pfa.gestionstock.service.PointDeVenteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pointdevente")
@CrossOrigin(origins = "*")
public class PointDeVenteController {

    private final PointDeVenteService service;

    public PointDeVenteController(PointDeVenteService service) {
        this.service = service;
    }

    @GetMapping
    public List<PointDeVente> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PointDeVente getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public PointDeVente save(@RequestBody PointDeVente pdv) {
        return service.save(pdv);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
