package org.rp.sandboxweb.dao.feed;

import org.rp.sandboxweb.dao.AbstractDAO;
import org.rp.sandboxweb.dao.DAOException;
import org.rp.sandboxweb.model.feed.Post;

import java.util.List;

public class PostDAO implements AbstractDAO<Post, Long> {

    public PostDAO() {
    }

    @Override
    public Post getById(Long id) throws DAOException {
        return null;
    }

    @Override
    public List<Post> getAll() throws DAOException {
        return null;
    }

    @Override
    public Long insert(Post model) throws DAOException {
        return null;
    }

    @Override
    public void update(Post model) throws DAOException {

    }

    @Override
    public void delete(Post model) throws DAOException {

    }
}
