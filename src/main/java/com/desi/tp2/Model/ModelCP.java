package com.desi.tp2.Model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class ModelCP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idComponentePrecio;

    private String nombreCP;

    private double precioCP;

    private double IVA;

    @Column(nullable = false )
    private double tasaAI;

    @Column( nullable = false )
    private double tasaAN;

    private int cotizacion;
}
