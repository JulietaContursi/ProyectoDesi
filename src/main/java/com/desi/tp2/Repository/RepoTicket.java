package com.desi.tp2.Repository;

import com.desi.tp2.Model.ModelTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoTicket extends JpaRepository<ModelTicket, Long> {

}
