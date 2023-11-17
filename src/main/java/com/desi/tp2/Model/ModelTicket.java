package com.desi.tp2.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class ModelTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long idTicket;

    @ManyToOne(targetEntity = ModelVuelo.class)
    private  ModelVuelo vuelo;

    @ManyToOne(targetEntity = ModelCliente.class)
    private ModelCliente cliente;

    private int asientoFila;

    private char asientoLetra;
}
