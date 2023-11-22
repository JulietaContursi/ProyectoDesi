package com.desi.tp2.Repository;

import com.desi.tp2.Model.ModelAvion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoAvion extends JpaRepository<ModelAvion, Long> {
}
