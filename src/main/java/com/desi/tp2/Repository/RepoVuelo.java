package com.desi.tp2.Repository;

import com.desi.tp2.Model.ModelVuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoVuelo extends JpaRepository <ModelVuelo, Long>{



}
