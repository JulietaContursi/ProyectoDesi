package com.desi.tp2.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Entity
public class ModelVuelo implements Comparable<ModelVuelo>{

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
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private tipoVuelo tipo;

    @Min(0)
    private double precioVuelo;

    @Column(nullable = false)
    private LocalDate fecha;
    
    @Column(nullable = false)
    private LocalTime hora;

    @ManyToOne(targetEntity = ModelAvion.class)
    private ModelAvion avion;

    public enum estadoVuelo {
        NORMAL, REPROGRAMADO, CANCELADO
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private estadoVuelo estado;

    @OneToMany(targetEntity = ModelTicket.class, fetch = FetchType.LAZY, mappedBy = "vuelo")
    private List<ModelTicket> ticket;

    @OneToMany(targetEntity = ModelAsiento.class, fetch = FetchType.LAZY, mappedBy = "vuelo")
    private List<ModelAsiento> asiento;
    
    @Min(0)
    @Column(nullable = false)
    private int asientosDeAvion;
    
    public ModelVuelo() {
    }

    public ModelVuelo(ModelCiudad ciudadOrigen, ModelCiudad ciudadDestino, tipoVuelo tipo, double precioVuelo, LocalDate fecha, LocalTime hora, ModelAvion avion, estadoVuelo estado, List<ModelTicket> ticket, int asientosDeAvion) {
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.tipo = tipo;
        this.precioVuelo = precioVuelo;
        this.fecha = fecha;
        this.hora = hora;
        this.avion = avion;
        this.estado = estado;
        this.ticket = ticket;
        this.asientosDeAvion = asientosDeAvion;
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

    public LocalDate getFecha() {
        return fecha;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() { return hora; }

    public void setHora(LocalTime hora) { this.hora = hora; }

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

    public List<ModelAsiento> getAsiento() {
		return asiento;
	}

	public void setAsiento(List<ModelAsiento> asiento) {
		this.asiento = asiento;
	}

	public int getAsientosDeAvion() {
		return asientosDeAvion;
	}

	public void setAsientosDeAvion(int asientosDeAvion) {
		this.asientosDeAvion = asientosDeAvion;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModelVuelo vuelo)) return false;
        return getIdVuelo() == vuelo.getIdVuelo() && Double.compare(vuelo.getPrecioVuelo(), 
        		getPrecioVuelo()) == 0 && Objects.equals(getCiudadOrigen(), 
        				vuelo.getCiudadOrigen()) && Objects.equals(getCiudadDestino(), 
        						vuelo.getCiudadDestino()) && getTipo() == vuelo.getTipo() && Objects.equals(getFecha(), 
        								vuelo.getFecha()) && Objects.equals(getHora(), 
        										vuelo.getHora()) && Objects.equals(getAvion(), 
        												vuelo.getAvion()) && getEstado() == vuelo.getEstado() && Objects.equals(getTicket(), 
        														vuelo.getTicket());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdVuelo(),
                getCiudadOrigen(),
                getCiudadDestino(),
                getTipo(),
                getPrecioVuelo(),
                getFecha(),
                getHora(),
                getAvion(),
                getEstado(),
                getTicket());
    }

    
    
    @Override
	public String toString() {
		return "ModelVuelo [idVuelo=" + idVuelo + ", ciudadOrigen=" + ciudadOrigen + ", ciudadDestino=" + ciudadDestino
				+ ", tipo=" + tipo + ", precioVuelo=" + precioVuelo + ", fecha=" + fecha + ", hora=" + hora + ", avion="
				+ avion + ", estado=" + estado + ", ticket=" + ticket + ", asiento=" + asiento + ", asientosDeAvion="
				+ asientosDeAvion + "]";
	}

	@Override
    public int compareTo(ModelVuelo otroVuelo) {
        return this.fecha.compareTo(otroVuelo.getFecha());
    }
}
