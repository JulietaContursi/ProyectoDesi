package com.desi.tp2.Repository;

import com.desi.tp2.Model.ModelAvion;
import com.desi.tp2.Model.ModelCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoAvion extends JpaRepository<ModelAvion, Long> {
    @Query("SELECT a FROM ModelAvion a WHERE a.nombre LIKE %?1%")
    public List<ModelAvion> findAllBy(String nombre);
}
