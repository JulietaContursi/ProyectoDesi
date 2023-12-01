package com.desi.tp2.Model;

import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class ModelAvion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAvion;

    @OneToMany(targetEntity = ModelVuelo.class, fetch = FetchType.LAZY, mappedBy = "avion")
    private List<ModelVuelo> vuelos;

    private String nombre;

    private int filas;

    private int asientosXFila;

    private String aerolinea;
    public ModelAvion() {

    }
    public ModelAvion(String nombre, int filas, int asientosXFila, String aerolinea) {
        this.nombre = nombre;
        this.filas = filas;
        this.asientosXFila = asientosXFila;
        this.aerolinea = aerolinea;
    }

    public long getIdAvion() {
        return idAvion;
    }

    public List<ModelVuelo> getVuelos() {
        return vuelos;
    }

    public void setVuelos(List<ModelVuelo> vuelos) {
        this.vuelos = vuelos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getAsientosXFila() {
        return asientosXFila;
    }

    public void setAsientosXFila(int asientosXFila) {
        this.asientosXFila = asientosXFila;
    }

    public String getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(String aerolinea) {
        this.aerolinea = aerolinea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModelAvion that)) return false;
        return getIdAvion() == that.getIdAvion() && getFilas() == that.getFilas() && getAsientosXFila() == that.getAsientosXFila() && Objects.equals(getVuelos(), that.getVuelos()) && Objects.equals(getNombre(), that.getNombre()) && Objects.equals(getAerolinea(), that.getAerolinea());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdAvion(), getVuelos(), getNombre(), getFilas(), getAsientosXFila(), getAerolinea());
    }

    @Override
    public String toString() {
        return nombre;
    }
}
