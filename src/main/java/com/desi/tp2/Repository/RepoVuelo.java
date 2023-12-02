package com.desi.tp2.Repository;

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

    @Query(" FROM ModelVuelo v WHERE v.fecha = :fecha ")
    List<ModelVuelo> findVuelosByFecha(@Param("fecha") Optional<LocalDate> fecha);

}
