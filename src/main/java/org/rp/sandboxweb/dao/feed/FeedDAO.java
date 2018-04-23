package org.rp.sandboxweb.dao.feed;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rp.sandboxweb.dao.AbstractDAO;
import org.rp.sandboxweb.dao.DAOException;
import org.rp.sandboxweb.dao.DBConnectionFactory;
import org.rp.sandboxweb.model.ModelException;
import org.rp.sandboxweb.model.Status;
import org.rp.sandboxweb.model.feed.Feed;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeedDAO implements AbstractDAO<Feed, Long> {

    private static Logger logger = LogManager.getLogger(FeedDAO.class);

    @Override
    public Feed getById(Long id) throws DAOException {

        Feed feed = null;
        ResultSet resultSet = null;

        try(Connection connection = DBConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM feed WHERE id=?")
        ) {

            statement.setLong(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                feed = this.createFeedFromResultSet(resultSet);
            }
        } catch (SQLException | ModelException e) {
            logger.error("Cannot get row by ID: " + e);
            throw new DAOException("Cannot get row by ID", e);
        }
        finally {

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return feed;

    }

    @Override
    public List<Feed> getAll() throws DAOException {

        List<Feed> feedList;

        try(Connection connection = DBConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM feed");
            ResultSet resultSet = statement.executeQuery()
        ) {

            feedList = new ArrayList();

            while (resultSet.next()) {
                feedList.add(this.createFeedFromResultSet(resultSet));
            }


        } catch (SQLException | ModelException e) {
            logger.error("Cannot get all rows: " + e);
            throw new DAOException("Cannot get all rows", e);
        }

        return feedList;
    }

    private Feed createFeedFromResultSet(ResultSet resultSet) throws SQLException, ModelException {

        Feed feed = new Feed();
        feed.setId(resultSet.getLong("id"));
        feed.setFeedUrl(resultSet.getString("feed_url"));
        feed.setLogoUrl(resultSet.getString("logo_url"));
        feed.setTitle(resultSet.getString("title"));
        feed.setDescription(resultSet.getString("description"));
        feed.setStatus(Status.valueOf(resultSet.getString("status")));
        feed.setAuthor(resultSet.getString("author"));
        feed.setNextJob(resultSet.getDate("next_job"));
        feed.setJobInterval(resultSet.getLong("job_interval"));
        return feed;
    }

    @Override
    public Long insert(Feed model) throws DAOException {
        return null;
    }

    @Override
    public void update(Feed model) throws DAOException {

    }

    @Override
    public void delete(Feed model) throws DAOException {

    }


}
