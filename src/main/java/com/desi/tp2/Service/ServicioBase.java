package com.desi.tp2.Service;

import java.util.List;
public interface ServicioBase<E> {
        List<E> buscarTodo() throws Exception;
        E buscarPorId(long id) throws Exception;
        E saveOne(E entity) throws Exception;
        E updateOne(E entity, long id) throws Exception;
        boolean deleteById(long id) throws Exception;
}
