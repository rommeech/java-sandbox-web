package org.rp.sandboxweb.dao;

import org.rp.sandboxweb.model.AbstractModel;
import org.rp.sandboxweb.model.feed.Feed;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

public interface AbstractDAO<T extends AbstractModel<K>, K extends Serializable> {

    public T getById(K id) throws DAOException;

    public List<T> getAll() throws DAOException;

    public K insert(T model) throws DAOException;

    public void update(T model) throws DAOException;

    public void delete(T model) throws DAOException;
}
