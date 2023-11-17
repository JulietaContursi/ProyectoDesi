package com.desi.tp2.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Data
@Entity
public class ModelVuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idvuelo")
    private long idVuelo;

    @OneToMany(targetEntity = ModelCiudad.class, fetch = FetchType.LAZY, mappedBy = "vueloOrigen")
    private List<ModelCiudad> origen;

    @OneToMany(targetEntity = ModelCiudad.class, fetch = FetchType.LAZY, mappedBy = "vueloDestino")
    private List<ModelCiudad> destino;

    public enum tipoVuelo {
        NACIONAL,INTERNACIONAL
    }
    @Column(nullable = false)
    private tipoVuelo tipo;

    @Column(name = "preciovuelo", nullable = false)
    private double precioVuelo;

    @Column(name = "fechahora", nullable = false)
    private LocalDate fechaHora;

    @OneToMany(targetEntity = ModelAvion.class, fetch = FetchType.LAZY, mappedBy = "vuelo")
    private List<ModelAvion> avion;

    public enum estadoVuelo{
        NORMAL, REPROGRAMADO,CANCELADO
    }
    @Column(nullable = false)
    private estadoVuelo estado;

    @OneToMany(targetEntity = ModelTicket.class, fetch = FetchType.LAZY, mappedBy = "vuelo")
    private List<ModelTicket> ticket;
}
