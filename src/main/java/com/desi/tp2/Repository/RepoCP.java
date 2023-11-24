package com.desi.tp2.Repository;

import com.desi.tp2.Model.ModelCP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoCP extends JpaRepository<ModelCP, Long> {
}
