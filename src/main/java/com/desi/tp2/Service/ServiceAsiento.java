package com.desi.tp2.Service;

import com.desi.tp2.Model.ModelAsiento;
import com.desi.tp2.Model.ModelVuelo;
import com.desi.tp2.Model.ModelAvion;
import com.desi.tp2.Repository.RepoAsiento;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceAsiento implements ServicioBase<ModelAsiento>{
    private final RepoAsiento repoAsiento;

    public ServiceAsiento(RepoAsiento repoAsientos) {
        this.repoAsiento = repoAsientos;
    }

    @Override
    public List<ModelAsiento> buscarTodo() throws Exception {
        List<ModelAsiento> asientos = this.repoAsiento.findAll();
        return asientos;
    }

    /*public List<ModelAsiento> findAsientos(Long id ){
    	List<ModelAsiento> asientos = this.repoAsiento.findAsientosVendidosByVuelo(id);
    	return asientos;
    }*/
    
    public long cantidadDeAsientoVendidos(ModelVuelo Vuelo) {
    	return repoAsiento.countAsientosVendidosByVuelo(Vuelo.getIdVuelo());
    }
    
    @Override
    public List<ModelAsiento> buscarTodo(ModelAsiento x) throws Exception {
        return null;
    }

    @Override
    public ModelAsiento buscarPorId(long id) throws Exception {
        Optional<ModelAsiento> opt = this.repoAsiento.findById(id);
        return opt.get();
    }

    @Override
    @Transactional
    public ModelAsiento guardar(ModelAsiento entity) throws Exception {
    	ModelAsiento ciudad = this.repoAsiento.save(entity);
        return ciudad;
    }

    @Override
    @Transactional
    public ModelAsiento actualizar(ModelAsiento entity, long id) throws Exception {
        Optional<ModelAsiento> opt = this.repoAsiento.findById(id);
        ModelAsiento ciudad = opt.get();
        ciudad = this.repoAsiento.save(entity);
        return ciudad;
    }

    @Override
    @Transactional
    public boolean borrar(long id) throws Exception {
        Optional<ModelAsiento> opt = this.repoAsiento.findById(id);
        if(!opt.isEmpty()){
            this.repoAsiento.deleteById(id);
        }else{
            throw new Exception();
        }
        return true;
    }

}