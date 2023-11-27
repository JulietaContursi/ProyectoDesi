package com.desi.tp2.Service;

import com.desi.tp2.Model.ModelCP;
import com.desi.tp2.Repository.RepoCP;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceTasa implements ServicioBase<ModelCP>{

    private final RepoCP repoCP;

    public ServiceTasa(RepoCP repoCP) {
        this.repoCP = repoCP;
    }

    @Override
    @Transactional
    public List<ModelCP> buscarTodo() throws Exception {
        //List<ModelCP> CP = this.repoCP.findAll();
        return this.repoCP.findAll();
    }

    @Override
    @Transactional
    public ModelCP buscarPorId(long id) throws Exception {
        Optional<ModelCP> opt = this.repoCP.findById(id);
        return opt.get(); // utilizamos un tipo Optional por si el valor no se encuentra en el sistema
    }

    @Override
    @Transactional
    public ModelCP guardar(ModelCP entity) throws Exception {
        ModelCP CP = this.repoCP.save(entity);
        return CP;
    }

    @Override
    @Transactional
    public ModelCP actualizar(ModelCP entity, long id) throws Exception {
        Optional<ModelCP> opt = this.repoCP.findById(id);
        ModelCP CP = opt.get();
        CP = this.repoCP.save(entity);
        return CP;
    }

    @Override
    @Transactional
    public boolean borrar(long id) throws Exception {
        Optional<ModelCP> opt = this.repoCP.findById(id);
        if(!opt.isEmpty()){
            ModelCP CP = opt.get();
            this.repoCP.save(CP);
        }else{
            throw new Exception();
        }
        return true;
    }
}
