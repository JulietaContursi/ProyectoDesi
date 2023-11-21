package com.desi.tp2.Service;

import com.desi.tp2.Model.ModelCiudad;
import com.desi.tp2.Repository.RepoCiudad;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceCiudad implements ServicioBase<ModelCiudad>{
    @Autowired
    private RepoCiudad repoCiudad;

    @Override
    @Transactional
    public List<ModelCiudad> buscarTodo() throws Exception {
        List<ModelCiudad> ciudades = this.repoCiudad.findAll();
        return ciudades;
    }

    @Override
    @Transactional
    public ModelCiudad buscarPorId(long id) throws Exception {
        Optional<ModelCiudad> opt = this.repoCiudad.findById(id);
        return opt.get();
    }

    @Override
    @Transactional
    public ModelCiudad saveOne(ModelCiudad entity) throws Exception {
        ModelCiudad ciudad = this.repoCiudad.save(entity);
        return ciudad;
    }

    @Override
    @Transactional
    public ModelCiudad updateOne(ModelCiudad entity, long id) throws Exception {
        Optional<ModelCiudad> opt = this.repoCiudad.findById(id);
        ModelCiudad ciudad = opt.get();
        ciudad = this.repoCiudad.save(entity);
        return ciudad;
    }

    @Override
    @Transactional
    public boolean deleteById(long id) throws Exception {
        Optional<ModelCiudad> opt = this.repoCiudad.findById(id);
        if(!opt.isEmpty()){
            ModelCiudad ciudad = opt.get();
            this.repoCiudad.save(ciudad);
        }else{
            throw new Exception();
        }
        return true;
    }
}