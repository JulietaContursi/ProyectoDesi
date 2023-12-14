package com.desi.tp2.Repository;

import com.desi.tp2.Model.ModelAsiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoAsiento extends JpaRepository<ModelAsiento, Long> {

	@Query("SELECT COUNT(*) FROM ModelAsiento a WHERE a.vuelo.idVuelo = :idVuelo AND a.estado = 'vendido'")
    long countByVueloIdAndEstadoVendido(@Param("idVuelo") long idVuelo);

}
