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

    @ManyToOne(targetEntity = ModelAvion.class) // innecesario
    private ModelAvion avion;
    
    @OneToOne(targetEntity = ModelAsiento.class, cascade=CascadeType.PERSIST)
    @JoinColumn(name="id_asiento")
    private ModelAvion asiento;
    
    private int asientoFila;

    private char asientoLetra;
    
    private double precio;

    private LocalDateTime fechaVuelo;

    private LocalDateTime fechaTicket;

    public ModelTicket() {
    }

    public ModelTicket(ModelVuelo vuelo, ModelCliente cliente, ModelAvion avion, ModelAvion asiento, int asientoFila,
			char asientoLetra, double precio, LocalDateTime fechaVuelo, LocalDateTime fechaTicket) {
		this.vuelo = vuelo;
		this.cliente = cliente;
		this.avion = avion;
		this.asiento = asiento;
		this.asientoFila = asientoFila;
		this.asientoLetra = asientoLetra;
		this.precio = precio;
		this.fechaVuelo = fechaVuelo;
		this.fechaTicket = fechaTicket;
	}



	public ModelAvion getAsiento() {
		return asiento;
	}

	public void setAsiento(ModelAvion asiento) {
		this.asiento = asiento;
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
	public int hashCode() {
		return Objects.hash(asiento, asientoFila, asientoLetra, avion, cliente, fechaTicket, fechaVuelo, idTicket,
				precio, vuelo);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelTicket other = (ModelTicket) obj;
		return Objects.equals(asiento, other.asiento) && asientoFila == other.asientoFila
				&& asientoLetra == other.asientoLetra && Objects.equals(avion, other.avion)
				&& Objects.equals(cliente, other.cliente) && Objects.equals(fechaTicket, other.fechaTicket)
				&& Objects.equals(fechaVuelo, other.fechaVuelo) && Objects.equals(idTicket, other.idTicket)
				&& Double.doubleToLongBits(precio) == Double.doubleToLongBits(other.precio)
				&& Objects.equals(vuelo, other.vuelo);
	}



	@Override
	public String toString() {
		return "ModelTicket [idTicket=" + idTicket + ", vuelo=" + vuelo + ", cliente=" + cliente + ", avion=" + avion
				+ ", asiento=" + asiento + ", asientoFila=" + asientoFila + ", asientoLetra=" + asientoLetra
				+ ", precio=" + precio + ", fechaVuelo=" + fechaVuelo + ", fechaTicket=" + fechaTicket + "]";
	}

    


}
