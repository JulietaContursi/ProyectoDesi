package com.desi.tp2.Service;

import com.desi.tp2.Model.ModelCiudad;
import com.desi.tp2.Repository.RepoCiudad;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceCiudad implements ServicioBase<ModelCiudad>{
    private final RepoCiudad repoCiudad;

    public ServiceCiudad(RepoCiudad repoCiudad) {
        this.repoCiudad = repoCiudad;
    }

    @Override
    @Transactional
    public List<ModelCiudad> buscarTodo() throws Exception {
        List<ModelCiudad> ciudades = this.repoCiudad.findAll();
        return ciudades;
    }

    @Override
    public List<ModelCiudad> buscarTodo(ModelCiudad x) throws Exception {
        return null;
    }

    @Override
    @Transactional
    public ModelCiudad buscarPorId(long id) throws Exception {
        Optional<ModelCiudad> opt = this.repoCiudad.findById(id);
        return opt.get();
    }

    @Override
    @Transactional
    public ModelCiudad guardar(ModelCiudad entity) throws Exception {
        ModelCiudad ciudad = this.repoCiudad.save(entity);
        return ciudad;
    }

    @Override
    @Transactional
    public ModelCiudad actualizar(ModelCiudad entity, long id) throws Exception {
        Optional<ModelCiudad> opt = this.repoCiudad.findById(id);
        ModelCiudad ciudad = opt.get();
        ciudad = this.repoCiudad.save(entity);
        return ciudad;
    }

    @Override
    @Transactional
    public boolean borrar(long id) throws Exception {
        Optional<ModelCiudad> opt = this.repoCiudad.findById(id);
        if(!opt.isEmpty()){
            ModelCiudad ciudad = opt.get();
            this.repoCiudad.deleteById(id);
        }else{
            throw new Exception();
        }
        return true;
    }
}