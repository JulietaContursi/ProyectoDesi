package com.desi.tp2.Model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "asientos_vendidos")
public class ModelAsiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long idAsiento;

    @ManyToOne
    @JoinColumn(name = "id_vuelo", nullable = false)
    private ModelVuelo vuelo;

    private int fila;
    private int columna;
    private String estado;
	
    public ModelAsiento() {}

	public ModelAsiento(ModelVuelo vuelo, int fila, int columna, String estado) {
		this.vuelo = vuelo;
		this.fila = fila;
		this.columna = columna;
		this.estado = estado;
	}

	public Long getIdAsiento() {
		return idAsiento;
	}

	public ModelVuelo getVuelo() {
		return vuelo;
	}

	public void setVuelo(ModelVuelo vuelo) {
		this.vuelo = vuelo;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(columna, estado, fila, idAsiento, vuelo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModelAsiento other = (ModelAsiento) obj;
		return columna == other.columna && Objects.equals(estado, other.estado) && fila == other.fila
				&& Objects.equals(idAsiento, other.idAsiento) && Objects.equals(vuelo, other.vuelo);
	}

	@Override
	public String toString() {
		return "ModelAsiento [idAsiento=" + idAsiento + ", vuelo=" + vuelo + ", fila=" + fila + ", columna=" + columna
				+ ", estado=" + estado + "]";
	}
    
    
}
