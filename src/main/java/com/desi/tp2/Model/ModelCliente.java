package com.desi.tp2.Model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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

    public ModelCliente() {
    }

    public ModelCliente(String apellidoNombre, String domicilio, String email, LocalDate fechaNacimiento, int numeroPasaporte) {
        this.apellidoNombre = apellidoNombre;
        this.domicilio = domicilio;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.numeroPasaporte = numeroPasaporte;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public String getApellidoNombre() {
        return apellidoNombre;
    }

    public void setApellidoNombre(String apellidoNombre) {
        this.apellidoNombre = apellidoNombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getNumeroPasaporte() {
        return numeroPasaporte;
    }

    public void setNumeroPasaporte(int numeroPasaporte) {
        this.numeroPasaporte = numeroPasaporte;
    }

    public List<ModelTicket> getTickets() {
        return tickets;
    }

    public void setTickets(List<ModelTicket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModelCliente that)) return false;
        return idCliente == that.idCliente && numeroPasaporte == that.numeroPasaporte && Objects.equals(apellidoNombre, that.apellidoNombre) && Objects.equals(domicilio, that.domicilio) && Objects.equals(email, that.email) && Objects.equals(fechaNacimiento, that.fechaNacimiento) && Objects.equals(tickets, that.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCliente, apellidoNombre, domicilio, email, fechaNacimiento, numeroPasaporte, tickets);
    }
}