package com.desi.tp2.Service;

import java.util.List;
public interface ServicioBase<E> {
        List<E> buscarTodo() throws Exception;
        E buscarPorId(long id) throws Exception;
        E guardar(E entity) throws Exception;
        E actualizar(E entity, long id) throws Exception;
        boolean borrar(long id) throws Exception;
}
