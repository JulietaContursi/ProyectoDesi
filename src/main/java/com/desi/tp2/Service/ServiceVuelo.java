package com.desi.tp2.Service;

import com.desi.tp2.Model.ModelAvion;
import com.desi.tp2.Model.ModelVuelo;
import com.desi.tp2.Repository.RepoAsiento;
import com.desi.tp2.Repository.RepoVuelo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceVuelo implements ServicioBase<ModelVuelo>{
    
	@Autowired
	private final RepoVuelo repoVuelo;
	
	@Autowired
	private RepoAsiento repoAsiento;
	
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
    public ModelVuelo buscarPorId(long id) throws Exception {
        Optional<ModelVuelo> opt = this.repoVuelo.findById(id);
        return opt.get();
    }

    public List<ModelVuelo> findVuelosByFecha(Optional<LocalDate> fecha) throws Exception{
        if(fecha.isPresent()){
        	List<ModelVuelo> lista = repoVuelo.findVuelosByFecha(fecha);
            return lista.stream()
                    .sorted(Comparator.comparing(ModelVuelo::getFecha))
                    .collect(Collectors.toList());
        }else{
        } return repoVuelo.findAll();
    }

    public List<ModelVuelo> findVuelosByFechaAndAvion(Optional<LocalDate> fecha, Optional<ModelAvion> avion) throws Exception{
        if(fecha.isPresent() && avion.isPresent()){
            return repoVuelo.findVuelosByFechaAndAvion(fecha, avion);
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
            this.repoVuelo.deleteById(id);
        }else{
            throw new Exception();
        }
        return true;
    }
    
    public List<ModelVuelo> ordenarPorFechaHora(List<ModelVuelo> vuelos){
    	vuelos.sort((v1, v2) -> {
            int comparacionFecha = v1.getFecha().compareTo(v2.getFecha());
            if (comparacionFecha == 0) {
                return v1.getHora().compareTo(v2.getHora());
            } else {
                return comparacionFecha;
            }
        });
		return vuelos;
    	
    }
    
    public int calcularCantidadAsientos(Optional<ModelAvion> avion) {
    	return avion.get().getFilas() * avion.get().getAsientosXFila();
    }
    
    public Map<Long, Integer> buscarAsientoLibres() {
        List<ModelVuelo> vuelos = this.repoVuelo.findAll();
        Map<Long, Integer> cantidadAsientos = new HashMap<>();
        for (ModelVuelo vuelo : vuelos) {
            long idVuelo = vuelo.getIdVuelo();
            int restantes = (int) (vuelo.getAsientosDeAvion() - repoAsiento.countByVueloIdAndEstadoVendido(idVuelo));
            cantidadAsientos.put(idVuelo, restantes);
        }
        return cantidadAsientos;
    }
    
    
    
}
