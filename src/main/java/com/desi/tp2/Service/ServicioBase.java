package com.desi.tp2.Service;

import com.desi.tp2.Model.ModelCliente;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ServicioBase<E> {
    List<E> buscarTodo() throws Exception;

    @Transactional
    List<E> buscarTodo(E dato) throws Exception;

    E buscarPorId(long id) throws Exception;
    @Transactional
    E guardar(E entity) throws Exception;
    @Transactional
    E actualizar(E entity, long id) throws Exception;
    @Transactional
    boolean borrar(long id) throws Exception;
}
