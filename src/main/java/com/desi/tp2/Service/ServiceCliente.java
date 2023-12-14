package com.desi.tp2.Service;
import com.desi.tp2.Model.ModelCliente;
import com.desi.tp2.Repository.RepoCliente;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceCliente implements ServicioBase<ModelCliente>{


    private final RepoCliente repoCliente;

    public ServiceCliente(RepoCliente repoCliente) {
        this.repoCliente = repoCliente;
    }

    @Override
    @Transactional
    public List<ModelCliente> buscarTodo() throws Exception {

        List<ModelCliente> clientes = this.repoCliente.findAll();
        return clientes;
    }

    @Override
    public List<ModelCliente> buscarTodo(ModelCliente x) throws Exception {
        return null;
    }


    public List<ModelCliente> buscarTodo(Optional<Integer> dni) throws Exception {
        if(dni.isPresent()) {
            return repoCliente.findAllBy(dni.get());
        }
        return repoCliente.findAll();
    }


    @Override
    @Transactional
    public ModelCliente buscarPorId(long id) throws Exception {
        Optional<ModelCliente> opt = this.repoCliente.findById(id);
        return opt.get();
    }

    @Override
    @Transactional
    public ModelCliente guardar(ModelCliente entity) throws Exception {
        ModelCliente cliente = this.repoCliente.save(entity);
        return cliente;
    }

    @Override
    @Transactional
    public ModelCliente actualizar(ModelCliente entity, long id) throws Exception {
        Optional<ModelCliente> opt = this.repoCliente.findById(id);
        ModelCliente cliente = opt.get();
        cliente = this.repoCliente.save(entity);
        return cliente;
    }

    @Override
    @Transactional
    public boolean borrar(long id) throws Exception{
        Optional<ModelCliente> opt = this.repoCliente.findById(id);
        if(!opt.isEmpty()){
        ModelCliente cliente = opt.get();
        this.repoCliente.save(cliente);
    }else{
        throw new Exception();
    }
        return true;
    }
}
