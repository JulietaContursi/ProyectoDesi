package com.desi.tp2.Service;

import com.desi.tp2.Model.ModelTicket;
import com.desi.tp2.Repository.RepoTicket;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceTicket implements ServicioBase<ModelTicket>{

    @Autowired
	private RepoTicket repoTicket;

    public ServiceTicket(RepoTicket repoTicket) {
        this.repoTicket = repoTicket;
    }

    @Override
    @Transactional
    public List<ModelTicket> buscarTodo() throws Exception {
        List<ModelTicket> tickets = this.repoTicket.findAll();
        return tickets;
    }

    @Override
    public List<ModelTicket> buscarTodo(ModelTicket x) throws Exception {
        return null;
    }

    @Override
    @Transactional
    public ModelTicket buscarPorId(long id) throws Exception {
        Optional<ModelTicket> opt = this.repoTicket.findById(id);
        return opt.get();
    }

    @Override
    @Transactional
    public ModelTicket guardar(ModelTicket entity) throws Exception {
        ModelTicket ticket = this.repoTicket.save(entity);
        return ticket;
    }

    @Override
    @Transactional
    public ModelTicket actualizar(ModelTicket entity, long id) throws Exception {
        Optional<ModelTicket> opt = this.repoTicket.findById(id);
        ModelTicket ticket = opt.get();
        ticket = this.repoTicket.save(entity);
        return ticket;
    }

    @Override
    @Transactional
    public boolean borrar(long id) throws Exception {
        Optional<ModelTicket> opt = this.repoTicket.findById(id);
        if(!opt.isEmpty()){
            ModelTicket ticket = opt.get();
            this.repoTicket.save(ticket);
        }else{
            throw new Exception();
        }
        return true;
    }
}
