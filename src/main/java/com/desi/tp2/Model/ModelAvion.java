package com.desi.tp2.Model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
public class ModelAvion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAvion;

    @OneToMany(targetEntity = ModelVuelo.class, fetch = FetchType.LAZY, mappedBy = "avion")
    private List<ModelVuelo> vuelos;

    private String nombre;

    private int filas;

    private int asientosXFila;

    private String aerolinea;
}
