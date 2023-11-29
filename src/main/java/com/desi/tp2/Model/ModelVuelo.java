package com.desi.tp2.Model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class ModelVuelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idVuelo;

    @ManyToOne(targetEntity = ModelCiudad.class)
    private ModelCiudad ciudadOrigen;

    @ManyToOne(targetEntity = ModelCiudad.class)
    private ModelCiudad ciudadDestino;

    public enum tipoVuelo {
        NACIONAL, INTERNACIONAL
    }

    @Enumerated(EnumType.STRING)
    private tipoVuelo tipo;

    private double precioVuelo;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime fechaHora;

    @ManyToOne(targetEntity = ModelAvion.class)
    private ModelAvion avion;

    public enum estadoVuelo {
        NORMAL, REPROGRAMADO, CANCELADO
    }

    @Enumerated(EnumType.STRING)
    private estadoVuelo estado;

    @OneToMany(targetEntity = ModelTicket.class, fetch = FetchType.LAZY, mappedBy = "vuelo")
    private List<ModelTicket> ticket;

}
