package com.desi.tp2.Service;

import com.desi.tp2.Model.ModelAsiento;
import com.desi.tp2.Model.ModelVuelo;
import com.desi.tp2.Model.ModelAvion;
import com.desi.tp2.Repository.RepoAsiento;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceAsiento implements ServicioBase<ModelAsiento>{

	@Autowired
	private RepoAsiento repoAsiento;
	
	
	@Override
	public List<ModelAsiento> buscarTodo() throws Exception {
		List<ModelAsiento> asientos = repoAsiento.findAll();
		return asientos;
	}

	@Override
	public List<ModelAsiento> buscarTodo(ModelAsiento id) throws Exception {

		return null;
	}

	@Override
	public ModelAsiento buscarPorId(long id) throws Exception {
		Optional<ModelAsiento> asiento = repoAsiento.findById(id);
		return asiento.get();
	}

	@Override
	public ModelAsiento guardar(ModelAsiento entity) throws Exception {
		this.repoAsiento.save(entity);
		return null;
	}

	@Override
	public ModelAsiento actualizar(ModelAsiento entity, long id) throws Exception {
		Optional<ModelAsiento> opt = this.repoAsiento.findById(id);
        if(opt.isPresent()) {
        	opt.get();
        	this.repoAsiento.save(entity);
        }
        return null;
	}

	@Override
	public boolean borrar(long id) throws Exception {
		Optional<ModelAsiento> opt = this.repoAsiento.findById(id);
        if(!opt.isEmpty()){
            this.repoAsiento.deleteById(id);
        }else{
            throw new Exception();
        }
        return true;
	}
	
	
	
	public long cantidadDeAsientosVendidos(long idVuelo) {
		
		return repoAsiento.countByVueloIdAndEstadoVendido(idVuelo); 
	}
	
	

}