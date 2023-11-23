package com.desi.tp2.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
public class ModelCiudad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCiudad;

    @OneToMany(targetEntity = ModelVuelo.class, fetch = FetchType.LAZY, mappedBy = "ciudadOrigen")
    private List<ModelVuelo> Origenes;

    @OneToMany(targetEntity = ModelVuelo.class, fetch = FetchType.LAZY, mappedBy = "ciudadDestino")
    private List<ModelVuelo> destinos;


    private String nombre;

    private int codigoPostal;

}
