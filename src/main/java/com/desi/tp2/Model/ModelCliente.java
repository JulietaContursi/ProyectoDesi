package com.desi.tp2.Model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class ModelCliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCliente;

    private String apellidoNombre;

    private String domicilio;

    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fechanacimiento")
    private LocalDate fechaNacimiento;

    private int numeroPasaporte;

    @OneToMany(targetEntity = ModelTicket.class, fetch = FetchType.LAZY, mappedBy = "cliente")
    private List<ModelTicket> tickets;


}