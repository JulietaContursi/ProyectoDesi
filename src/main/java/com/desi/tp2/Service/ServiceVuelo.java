package com.desi.tp2.Service;

import com.desi.tp2.Model.ModelVuelo;
import com.desi.tp2.Repository.RepoVuelo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.*;

@Service
public class ServiceVuelo implements ServicioBase<ModelVuelo>{
    private final RepoVuelo repoVuelo;

    public ServiceVuelo(RepoVuelo repoVuelo) {
        this.repoVuelo = repoVuelo;
    }

    @Override
    @Transactional
    public List<ModelVuelo> buscarTodo() throws Exception {
        List<ModelVuelo> vuelos = this.repoVuelo.findAll();
        return vuelos;
    }

    @Override
    public List<ModelVuelo> buscarTodo(ModelVuelo dato) throws Exception {
        return null;
    }

    @Override
    @Transactional
    public ModelVuelo buscarPorId(long id) throws Exception {
        Optional<ModelVuelo> opt = this.repoVuelo.findById(id);
        return opt.get();
    }

    public List<ModelVuelo> findVuelosByFecha(Optional<LocalDate> fecha) throws Exception{
        if(fecha.isPresent()){
            return repoVuelo.findVuelosByFecha(fecha);
        }else{
        } return repoVuelo.findAll();
    }

    @Override
    @Transactional
    public ModelVuelo guardar(ModelVuelo entity) throws Exception {
        ModelVuelo vuelo = this.repoVuelo.save(entity);
        return vuelo;
    }

    @Override
    @Transactional
    public ModelVuelo actualizar(ModelVuelo entity, long id) throws Exception {
        Optional<ModelVuelo> opt = this.repoVuelo.findById(id);
        ModelVuelo vuelo = opt.get();
        vuelo = this.repoVuelo.save(entity);
        return vuelo;
    }

    @Override
    @Transactional
    public boolean borrar(long id) throws Exception {
        Optional<ModelVuelo> opt = this.repoVuelo.findById(id);
        if(!opt.isEmpty()){
            ModelVuelo vuelo = opt.get();
            this.repoVuelo.deleteById(id);
        }else{
            throw new Exception();
        }
        return true;
    }
}
