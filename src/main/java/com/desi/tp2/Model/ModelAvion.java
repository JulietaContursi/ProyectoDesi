package com.desi.tp2.Model;


import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class ModelAvion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idavion")
    private long idAvion;

    @ManyToOne(targetEntity = ModelVuelo.class)
    private ModelVuelo vuelo;

    private String nombre;

    private int filas;

    private int asientosXFila;

    private String aerolinea;
}
