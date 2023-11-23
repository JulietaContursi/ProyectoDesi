package com.desi.tp2.Repository;

import com.desi.tp2.Model.ModelCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoCliente extends JpaRepository<ModelCliente, Long> {
}
