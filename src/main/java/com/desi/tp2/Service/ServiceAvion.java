package com.desi.tp2.Service;

import com.desi.tp2.Model.ModelAvion;
import com.desi.tp2.Model.ModelCliente;
import com.desi.tp2.Repository.RepoAvion;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceAvion implements ServicioBase<ModelAvion>{


    private final RepoAvion repoAvion;

    public ServiceAvion(RepoAvion repoAvion) {
        this.repoAvion = repoAvion;
    }

    @Override
    @Transactional
    public List<ModelAvion> buscarTodo() throws Exception {
        List<ModelAvion> aviones = this.repoAvion.findAll();
        return aviones;
    }

    @Override
    public List<ModelAvion> buscarTodo(ModelAvion x) throws Exception {
        return null;
    }

    public List<ModelCliente> buscarTodo(String nombre) throws Exception {
        List<ModelAvion> aviones = this.repoAvion.findAllBy(nombre);
        return null;
    }

    @Override
    @Transactional
    public ModelAvion buscarPorId(long id) throws Exception {
        Optional<ModelAvion> opt = this.repoAvion.findById(id);
        return opt.get();
    }

    @Override
    @Transactional
    public ModelAvion guardar(ModelAvion entity) throws Exception {
        ModelAvion avion = this.repoAvion.save(entity);
        return avion;
    }

    @Override
    @Transactional
    public ModelAvion actualizar(ModelAvion entity, long id) throws Exception {
        Optional<ModelAvion> opt = this.repoAvion.findById(id);
        ModelAvion avion = opt.get();
        avion = this.repoAvion.save(entity);
        return avion;
    }

    @Override
    @Transactional
    public boolean borrar(long id) throws Exception{
        Optional<ModelAvion> opt = this.repoAvion.findById(id);
        if(!opt.isEmpty()){
        ModelAvion avion = opt.get();
        this.repoAvion.save(avion);
    }else{
        throw new Exception();
    }
        return true;
    }
    public int cantidadAsientosAvion(ModelAvion avion) {

    	return avion.getFilas() * avion.getAsientosXFila();
    }
}
