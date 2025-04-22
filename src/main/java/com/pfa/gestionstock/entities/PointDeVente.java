package com.pfa.gestionstock.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "points_de_vente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PointDeVente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String adresse;

   
}
