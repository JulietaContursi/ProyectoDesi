package com.desi.tp2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.Objects;

@Entity
public class ModelCP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idComponentePrecio;

    private String nombreCP;

    @Min(0)
    private double precioCP;

    @Min(0)@Max(100)
    private double IVA;

    @Min(0)
    @Column(nullable = false )
    private double tasaAI;

    @Min(0)
    @Column( nullable = false )
    private double tasaAN;

    @Min(0)
    private int cotizacion;

    public ModelCP() {
    }

    public ModelCP(String nombreCP, double precioCP, double IVA, double tasaAI, double tasaAN, int cotizacion) {
        this.nombreCP = nombreCP;
        this.precioCP = precioCP;
        this.IVA = IVA;
        this.tasaAI = tasaAI;
        this.tasaAN = tasaAN;
        this.cotizacion = cotizacion;
    }

    public long getIdComponentePrecio() {
        return idComponentePrecio;
    }

    public String getNombreCP() {
        return nombreCP;
    }

    public void setNombreCP(String nombreCP) {
        this.nombreCP = nombreCP;
    }

    public double getPrecioCP() {
        return precioCP;
    }

    public void setPrecioCP(double precioCP) {
        this.precioCP = precioCP;
    }

    public double getIVA() {
        return IVA;
    }

    public void setIVA(double IVA) {
        this.IVA = IVA;
    }

    public double getTasaAI() {
        return tasaAI;
    }

    public void setTasaAI(double tasaAI) {
        this.tasaAI = tasaAI;
    }

    public double getTasaAN() {
        return tasaAN;
    }

    public void setTasaAN(double tasaAN) {
        this.tasaAN = tasaAN;
    }

    public int getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(int cotizacion) {
        this.cotizacion = cotizacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModelCP modelCP)) return false;
        return idComponentePrecio == modelCP.idComponentePrecio && Double.compare(modelCP.precioCP, precioCP) == 0 && Double.compare(modelCP.IVA, IVA) == 0 && Double.compare(modelCP.tasaAI, tasaAI) == 0 && Double.compare(modelCP.tasaAN, tasaAN) == 0 && cotizacion == modelCP.cotizacion && Objects.equals(nombreCP, modelCP.nombreCP);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idComponentePrecio, nombreCP, precioCP, IVA, tasaAI, tasaAN, cotizacion);
    }
}
