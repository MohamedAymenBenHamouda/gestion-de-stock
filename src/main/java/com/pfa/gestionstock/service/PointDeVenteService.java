package com.pfa.gestionstock.service;

import com.pfa.gestionstock.entities.PointDeVente;
import com.pfa.gestionstock.repository.PointDeVenteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointDeVenteService {

    private final PointDeVenteRepository repository;

    public PointDeVenteService(PointDeVenteRepository repository) {
        this.repository = repository;
    }

    public List<PointDeVente> getAll() {
        return repository.findAll();
    }

    public PointDeVente getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public PointDeVente save(PointDeVente pdv) {
        return repository.save(pdv);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
