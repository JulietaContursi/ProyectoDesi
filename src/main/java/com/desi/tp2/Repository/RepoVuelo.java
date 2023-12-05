package com.desi.tp2.Repository;

import com.desi.tp2.Model.ModelAvion;
import com.desi.tp2.Model.ModelVuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RepoVuelo extends JpaRepository <ModelVuelo, Long>{

    @Query(" FROM ModelVuelo v WHERE v.fecha = :fecha ORDER BY v.fecha")
    List<ModelVuelo> findVuelosByFecha(@Param("fecha") Optional<LocalDate> fecha);

    @Query(" FROM ModelVuelo v WHERE v.fecha = :fecha AND v.avion = :avion")
    List<ModelVuelo> findVuelosByFechaAndAvion(@Param("fecha") Optional<LocalDate> fecha, @Param("avion")Optional<ModelAvion> avion);

}
