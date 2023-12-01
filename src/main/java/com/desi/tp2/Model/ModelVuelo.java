package com.desi.tp2.Model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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

    public ModelVuelo() {
    }

    public ModelVuelo(ModelCiudad ciudadOrigen, ModelCiudad ciudadDestino, tipoVuelo tipo, double precioVuelo, LocalDateTime fechaHora, ModelAvion avion, estadoVuelo estado, List<ModelTicket> ticket) {
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.tipo = tipo;
        this.precioVuelo = precioVuelo;
        this.fechaHora = fechaHora;
        this.avion = avion;
        this.estado = estado;
        this.ticket = ticket;
    }

    public long getIdVuelo() {
        return idVuelo;
    }

    public ModelCiudad getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(ModelCiudad ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public ModelCiudad getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(ModelCiudad ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public tipoVuelo getTipo() {
        return tipo;
    }

    public void setTipo(tipoVuelo tipo) {
        this.tipo = tipo;
    }

    public double getPrecioVuelo() {
        return precioVuelo;
    }

    public void setPrecioVuelo(double precioVuelo) {
        this.precioVuelo = precioVuelo;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public ModelAvion getAvion() {
        return avion;
    }

    public void setAvion(ModelAvion avion) {
        this.avion = avion;
    }

    public estadoVuelo getEstado() {
        return estado;
    }

    public void setEstado(estadoVuelo estado) {
        this.estado = estado;
    }

    public List<ModelTicket> getTicket() {
        return ticket;
    }

    public void setTicket(List<ModelTicket> ticket) {
        this.ticket = ticket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModelVuelo vuelo)) return false;
        return getIdVuelo() == vuelo.getIdVuelo() && Double.compare(vuelo.getPrecioVuelo(), getPrecioVuelo()) == 0 && Objects.equals(getCiudadOrigen(), vuelo.getCiudadOrigen()) && Objects.equals(getCiudadDestino(), vuelo.getCiudadDestino()) && getTipo() == vuelo.getTipo() && Objects.equals(getFechaHora(), vuelo.getFechaHora()) && Objects.equals(getAvion(), vuelo.getAvion()) && getEstado() == vuelo.getEstado() && Objects.equals(getTicket(), vuelo.getTicket());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdVuelo(), getCiudadOrigen(), getCiudadDestino(), getTipo(), getPrecioVuelo(), getFechaHora(), getAvion(), getEstado(), getTicket());
    }

    @Override
    public String toString() {
        return "ModelVuelo{" +
                "idVuelo=" + idVuelo +
                ", ciudadOrigen=" + ciudadOrigen +
                ", ciudadDestino=" + ciudadDestino +
                ", tipo=" + tipo +
                ", precioVuelo=" + precioVuelo +
                ", fechaHora=" + fechaHora +
                ", avion=" + avion +
                ", estado=" + estado +
                ", ticket=" + ticket +
                '}';
    }
}
