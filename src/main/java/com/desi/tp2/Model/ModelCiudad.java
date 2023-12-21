package com.desi.tp2.Model;
import jakarta.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class ModelCiudad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCiudad;

    @OneToMany(targetEntity = ModelVuelo.class, fetch = FetchType.LAZY, mappedBy = "ciudadOrigen")
    private List<ModelVuelo> Origenes;

    @OneToMany(targetEntity = ModelVuelo.class, fetch = FetchType.LAZY, mappedBy = "ciudadDestino")
    private List<ModelVuelo> destinos;


    private String nombre;

    private int codigoPostal;

    public ModelCiudad() {
    }
    public ModelCiudad(long idCiudad,String nombre, int codigoPostal) {
        this.idCiudad = idCiudad;
        this.nombre = nombre;
        this.codigoPostal = codigoPostal;
    }
    public ModelCiudad(String nombre, int codigoPostal) {
        this.nombre = nombre;
        this.codigoPostal = codigoPostal;
    }

    public long getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(long idCiudad) {
        this.idCiudad = idCiudad;
    }
    public List<ModelVuelo> getOrigenes() {
        return Origenes;
    }

    public void setOrigenes(List<ModelVuelo> origenes) {
        Origenes = origenes;
    }

    public List<ModelVuelo> getDestinos() {
        return destinos;
    }

    public void setDestinos(List<ModelVuelo> destinos) {
        this.destinos = destinos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @Override
    public String toString() {
        return  nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModelCiudad that)) return false;
        return getIdCiudad() == that.getIdCiudad() && getCodigoPostal() == that.getCodigoPostal() && Objects.equals(getOrigenes(), that.getOrigenes()) && Objects.equals(getDestinos(), that.getDestinos()) && Objects.equals(getNombre(), that.getNombre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdCiudad(), getOrigenes(), getDestinos(), getNombre(), getCodigoPostal());
    }
}
