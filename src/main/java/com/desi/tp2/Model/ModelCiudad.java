package com.desi.tp2.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class ModelCiudad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCiudad;

    @ManyToOne(targetEntity = ModelVuelo.class)
    private ModelVuelo vueloOrigen;

    @ManyToOne(targetEntity = ModelVuelo.class)
    private ModelVuelo vueloDestino;

    private String nombre;

    private int CP;

}
