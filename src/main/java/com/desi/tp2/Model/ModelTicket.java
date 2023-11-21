package com.desi.tp2.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class ModelTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long idTicket;

    @ManyToOne(targetEntity = ModelVuelo.class)
    private  ModelVuelo vuelo;

    @OneToMany(targetEntity = ModelVuelo.class, fetch = FetchType.LAZY, mappedBy = "ticket")
    private List<ModelVuelo> ticket;

    @ManyToOne(targetEntity = ModelCliente.class)
    private ModelCliente cliente;

    private int asientoFila;

    private char asientoLetra;

    private double precio;

    private LocalDate fecha;
}
