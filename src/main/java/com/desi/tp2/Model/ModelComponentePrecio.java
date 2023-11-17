package com.desi.tp2.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Data
@Entity
public class ModelComponentePrecio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComponentePrecio;

    private String nombreCP;

    private double precioCP;

    private double IVA;

    private double tasaAeroportuaria;

    private int cotización;
}
