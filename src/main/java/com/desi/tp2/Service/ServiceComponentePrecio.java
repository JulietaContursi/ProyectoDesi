package com.desi.tp2.Service;

import com.desi.tp2.Model.ModelComponentePrecio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceComponentePrecio implements ServicioBase<ModelComponentePrecio>{


    @Override
    public List<ModelComponentePrecio> buscarTodo() throws Exception {
        return null;
    }

    @Override
    public ModelComponentePrecio buscarPorId(long id) throws Exception {
        return null;
    }

    @Override
    public ModelComponentePrecio guardar(ModelComponentePrecio entity) throws Exception {
        return null;
    }

    @Override
    public ModelComponentePrecio actualizar(ModelComponentePrecio entity, long id) throws Exception {
        return null;
    }

    @Override
    public boolean borrar(long id) throws Exception {
        return false;
    }
}
