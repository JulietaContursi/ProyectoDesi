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
    @Column(name="id_asiento")
    private Long idAsiento;

    @ManyToOne
    @JoinColumn(name = "id_vuelo", nullable = false)
    private ModelVuelo vuelo;

    @ManyToOne
    @JoinColumn(name = "id_avion", nullable = false)
    private ModelAvion avion;
    
    

	public ModelAsiento() {
		super();
	}

	public ModelAsiento(ModelVuelo vuelo, ModelAvion avion) {
		this.vuelo = vuelo;
		this.avion = avion;
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

	public ModelAvion getAvion() {
		return avion;
	}

	public void setAvion(ModelAvion avion) {
		this.avion = avion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(avion, idAsiento, vuelo);
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
		return Objects.equals(avion, other.avion) && Objects.equals(idAsiento, other.idAsiento)
				&& Objects.equals(vuelo, other.vuelo);
	}

	@Override
	public String toString() {
		return "ModelAsientos [idAsiento=" + idAsiento + ", vuelo=" + vuelo + ", avion=" + avion + "]";
	}

    

    
}
