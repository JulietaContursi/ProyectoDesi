package com.desi.tp2.Repository;

import com.desi.tp2.Model.ModelCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoCliente extends JpaRepository<ModelCliente, Long> {

    @Query("SELECT c FROM ModelCliente c WHERE cast(c.dni as string) like %:numero%")
    List<ModelCliente> findAllBy(@Param("numero") int dni);
}
