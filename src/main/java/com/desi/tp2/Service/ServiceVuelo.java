package com.desi.tp2.Service;

import com.desi.tp2.Model.ModelVuelo;
import com.desi.tp2.Repository.RepoVuelo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceVuelo implements ServicioBase<ModelVuelo>{
    @Autowired
    private RepoVuelo repoVuelo;

    @Override
    @Transactional
    public List<ModelVuelo> buscarTodo() throws Exception {
        return null;
    }

    @Override
    @Transactional
    public ModelVuelo buscarPorId(long id) throws Exception {
        return null;
    }

    @Override
    @Transactional
    public ModelVuelo saveOne(ModelVuelo entity) throws Exception {
        ModelVuelo vuelo = this.repoVuelo.save(entity);
        return vuelo;
    }

    @Override
    @Transactional
    public ModelVuelo updateOne(ModelVuelo entity, long id) throws Exception {
        Optional<ModelVuelo> opt = this.repoVuelo.findById(id);
        ModelVuelo vuelo = opt.get();
        vuelo = this.repoVuelo.save(entity);
        return vuelo;
    }

    @Override
    @Transactional
    public boolean deleteById(long id) throws Exception {
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
