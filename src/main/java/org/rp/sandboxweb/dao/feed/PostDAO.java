package org.rp.sandboxweb.dao.feed;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rp.sandboxweb.dao.AbstractDAO;
import org.rp.sandboxweb.dao.DAOException;
import org.rp.sandboxweb.dao.DBConnectionFactory;
import org.rp.sandboxweb.model.ModelException;
import org.rp.sandboxweb.model.Status;
import org.rp.sandboxweb.model.feed.Feed;
import org.rp.sandboxweb.model.feed.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PostDAO implements AbstractDAO<Post, Long> {

    private static Logger logger = LogManager.getLogger(FeedDAO.class);

    private static final String SQL_GET_BY_ID = "SELECT * FROM post WHERE id=?";

    private static final String SQL_GET_ALL = "SELECT * FROM post";

    private static final String SQL_INSERT = "INSERT INTO post " +
            "(feed, xid, title, content, author, url) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE post SET feed=?, xid=?, title=?, content=?, author=?, url=? " +
            "WHERE id=?";

    private static final String SQL_DELETE = "DELETE FROM post WHERE id=?";

    public PostDAO() {
    }

    public Post getById(Long id) throws DAOException {

        Post post = null;
        ResultSet resultSet = null;

        try(Connection connection = DBConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM post WHERE id=?")
        ) {
            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                post = this.createFeedFromResultSet(resultSet);
            }
        } catch (SQLException | ModelException | DAOException e) {
            logger.error("PostFAO: cannot get row by ID: " + e);
            throw new DAOException("PostDAO: cannot get row by ID", e);
        }
        finally {

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    logger.error("PostDAO: Cannot close ResultSet " + e);
                    throw new DAOException("PostDAO: Cannot close ResultSet", e);
                }
            }
        }

        return post;

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

    private Post createFeedFromResultSet(ResultSet resultSet) throws SQLException, ModelException {
        Post post = new Post();
        post.setId(resultSet.getLong("id"));
        post.setXid("xid");
        post.setAuthor("author");
        post.setContent("content");
        post.setUrl("url");
        return post;
    }
}
