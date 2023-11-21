package com.desi.tp2.Model;

import jakarta.persistence.*;
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

    @Column(name = "tasainternacional", nullable = false )
    private double tasaAeroportuariaInternacional;
    @Column(name = "tasanacional")
    private double tasaAeroportuariaNacional;

    private int cotización;
}
