package org.rp.sandboxweb.dao;

import org.rp.sandboxweb.model.AbstractModel;

import java.io.Serializable;
import java.util.List;

public interface AbstractDAO<T extends AbstractModel<K>, K extends Serializable> {

    T getById(K id) throws ExceptionDAO;

    List<T> getAll() throws ExceptionDAO;

    void insert(T model) throws ExceptionDAO;

    void update(T model) throws ExceptionDAO;

    void delete(T model) throws ExceptionDAO;

}
