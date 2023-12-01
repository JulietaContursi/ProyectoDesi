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

    private int dni;

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

    public ModelCliente(String apellidoNombre, int dni, String domicilio, String email, LocalDate fechaNacimiento, int numeroPasaporte) {
        this.apellidoNombre = apellidoNombre;
        this.dni = dni;
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

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
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
        return getIdCliente() == that.getIdCliente() && getDni() == that.getDni() && getNumeroPasaporte() == that.getNumeroPasaporte() && Objects.equals(getApellidoNombre(), that.getApellidoNombre()) && Objects.equals(getDomicilio(), that.getDomicilio()) && Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getFechaNacimiento(), that.getFechaNacimiento()) && Objects.equals(getTickets(), that.getTickets());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdCliente(), getApellidoNombre(), getDni(), getDomicilio(), getEmail(), getFechaNacimiento(), getNumeroPasaporte(), getTickets());
    }

    @Override
    public String toString() {
        return apellidoNombre;
    }
}