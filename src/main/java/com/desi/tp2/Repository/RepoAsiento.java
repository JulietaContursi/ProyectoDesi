package com.desi.tp2.Repository;

import com.desi.tp2.Model.ModelAsiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoAsiento extends JpaRepository<ModelAsiento, Long> {

	//@Query("SELECT a FROM ModelAsientos a WHERE a.id_vuelo = :vueloId")
	//List<ModelAsiento> findAsientosVendidosByVuelo(@Param("vueloId") Long vueloId);
	
	@Query("SELECT COUNT(a) FROM ModelAsiento a WHERE a.vuelo.idVuelo = :vueloId")
	int countAsientosVendidosByVuelo(@Param("vueloId") Long vueloId);
}
