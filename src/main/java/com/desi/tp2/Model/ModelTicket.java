package com.desi.tp2.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class ModelTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long idTicket;

    @ManyToOne(targetEntity = ModelVuelo.class)
    private  ModelVuelo vuelo;

    @ManyToOne(targetEntity = ModelCliente.class)
    private ModelCliente cliente;

    private int asientoFila;

    private char asientoLetra;

    private double precio;

    private LocalDateTime fechaVuelo;

    private LocalDateTime fechaTicket;

    public ModelTicket() {
    }

    public ModelTicket(ModelVuelo vuelo, ModelCliente cliente, int asientoFila, char asientoLetra, double precio, LocalDateTime fechaVuelo, LocalDateTime fechaTicket) {
        this.vuelo = vuelo;
        this.cliente = cliente;
        this.asientoFila = asientoFila;
        this.asientoLetra = asientoLetra;
        this.precio = precio;
        this.fechaVuelo = fechaVuelo;
        this.fechaTicket = fechaTicket;
    }

    public Long getIdTicket() {
        return idTicket;
    }

    public ModelVuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(ModelVuelo vuelo) {
        this.vuelo = vuelo;
    }

    public ModelCliente getCliente() {
        return cliente;
    }

    public void setCliente(ModelCliente cliente) {
        this.cliente = cliente;
    }

    public int getAsientoFila() {
        return asientoFila;
    }

    public void setAsientoFila(int asientoFila) {
        this.asientoFila = asientoFila;
    }

    public char getAsientoLetra() {
        return asientoLetra;
    }

    public void setAsientoLetra(char asientoLetra) {
        this.asientoLetra = asientoLetra;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDateTime getFechaVuelo() {
        return fechaVuelo;
    }

    public void setFechaVuelo(LocalDateTime fechaVuelo) {
        this.fechaVuelo = fechaVuelo;
    }

    public LocalDateTime getFechaTicket() {
        return fechaTicket;
    }

    public void setFechaTicket(LocalDateTime fechaTicket) {
        this.fechaTicket = fechaTicket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModelTicket that)) return false;
        return asientoFila == that.asientoFila && asientoLetra == that.asientoLetra && Double.compare(that.precio, precio) == 0 && Objects.equals(idTicket, that.idTicket) && Objects.equals(vuelo, that.vuelo) && Objects.equals(cliente, that.cliente) && Objects.equals(fechaVuelo, that.fechaVuelo) && Objects.equals(fechaTicket, that.fechaTicket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTicket, vuelo, cliente, asientoFila, asientoLetra, precio, fechaVuelo, fechaTicket);
    }


}
