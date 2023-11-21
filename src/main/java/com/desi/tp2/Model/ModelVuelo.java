package com.desi.tp2.Model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity
public class ModelVuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idvuelo")
    private long idVuelo;

    @ManyToOne(targetEntity = ModelCiudad.class)
    private ModelCiudad ciudadOrigen;

    @ManyToOne(targetEntity = ModelCiudad.class)
    private ModelCiudad ciudadDestino;

    public enum tipoVuelo {
        NACIONAL,INTERNACIONAL
    }
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private tipoVuelo tipo;

    @Column(name = "preciovuelo", nullable = false)
    private double precioVuelo;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "fechahora", nullable = false)
    private LocalDate fechaHora;

    @ManyToOne(targetEntity = ModelAvion.class)
    private ModelAvion avion;


    public enum estadoVuelo{
        NORMAL, REPROGRAMADO,CANCELADO
    }
    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private estadoVuelo estado;

    @ManyToOne(targetEntity = ModelTicket.class)
    private ModelTicket ticket;
}
