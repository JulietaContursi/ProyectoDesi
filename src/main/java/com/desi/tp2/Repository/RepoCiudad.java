package com.desi.tp2.Repository;

import com.desi.tp2.Model.ModelCiudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoCiudad extends JpaRepository<ModelCiudad, Long> {



}
