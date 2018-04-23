package org.rp.sandboxweb.dao;

import org.rp.sandboxweb.model.AbstractModel;

import java.io.Serializable;
import java.util.List;

public interface AbstractDAO<T extends AbstractModel<K>, K extends Serializable> {

    T getById(K id) throws DAOException;

    List<T> getAll() throws DAOException;

    K insert(T model) throws DAOException;

    void update(T model) throws DAOException;

    void delete(T model) throws DAOException;

}
