package org.rp.sandboxweb.dao.feed;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rp.sandboxweb.dao.AbstractDAO;
import org.rp.sandboxweb.dao.DAOException;
import org.rp.sandboxweb.dao.DBConnectionFactory;
import org.rp.sandboxweb.model.ModelException;
import org.rp.sandboxweb.model.Status;
import org.rp.sandboxweb.model.feed.Feed;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedDAO implements AbstractDAO<Feed, Long> {

    private static Logger logger = LogManager.getLogger(FeedDAO.class);

    private static final String SQL_INSERT = "INSERT INTO feed " +
            "(feed_url, logo_url, title, description, status, author, next_job, job_interval) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE feed SET feed_url=?, logo_url=?, title=?," +
            "description=?, status=?, author=?, next_job=?, job_interval=? WHERE id=?";

    private static final String SQL_DELETE = "DELETE FROM feed WHERE id=?";


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
        } catch (SQLException | ModelException | DAOException e) {
            logger.error("FeedDAO: cannot get row by ID: " + e);
            throw new DAOException("FeedDAO: cannot get row by ID", e);
        }
        finally {

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    logger.error("FeedDAO: cannot close ResultSet " + e);
                    throw new DAOException("FeedDAO: c annot close ResultSet", e);
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

            feedList = new ArrayList<>();

            while (resultSet.next()) {
                feedList.add(this.createFeedFromResultSet(resultSet));
            }


        } catch (SQLException | ModelException | DAOException e) {
            logger.error("Cannot get all rows: " + e);
            throw new DAOException("Cannot get all rows", e);
        }

        return feedList;
    }

    @Override
    public Long insert(Feed model) throws DAOException {

        Long id = null;
        ResultSet keySet = null;

        try(Connection connection = DBConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)
        ) {

            // Fields order: feed_url, logo_url, title, description, status, author, next_job, job_interval
            statement.setString(1, model.getFeedUrl());
            statement.setString(2, model.getLogoUrl());
            statement.setString(3, model.getTitle());
            statement.setString(4, model.getDescription());
            statement.setString(5, model.getStatus().name());
            statement.setString(6, model.getAuthor());
            statement.setDate(7, model.getNextJob());
            statement.setInt(8, model.getJobInterval());
            statement.execute();

            // Get inserted ID
            keySet = statement.getGeneratedKeys();
            if (keySet != null && keySet.next()) {
                id = keySet.getLong(1);
            }

            model.setId(id);

        } catch (SQLException | ModelException | DAOException e) {
            logger.error("Cannot insert row: " + e);
            throw new DAOException("Cannot insert row", e);
        }
        finally {
            if (keySet != null) {
                try {
                    keySet.close();
                } catch (SQLException e) {
                    logger.error("Cannot close keyResultSet: " + e);
                    throw new DAOException("Cannot close keyResultSet", e);
                }
            }
        }

        return model.getId();
    }

    @Override
    public void update(Feed model) throws DAOException {

        try(Connection connection = DBConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)
        ) {

            // Fields order: feed_url, logo_url, title, description, status, author, next_job, job_interval, ID
            statement.setString(1, model.getFeedUrl());
            statement.setString(2, model.getLogoUrl());
            statement.setString(3, model.getTitle());
            statement.setString(4, model.getDescription());
            statement.setString(5, model.getStatus().name());
            statement.setString(6, model.getAuthor());
            statement.setDate(7, model.getNextJob());
            statement.setInt(8, model.getJobInterval());
            statement.setLong(9, model.getId());
            statement.execute();

        } catch (SQLException | DAOException e) {
            logger.error("Cannot insert row: " + e);
            throw new DAOException("Cannot insert row", e);
        }
    }

    @Override
    public void delete(Feed model) throws DAOException {
        try(Connection connection = DBConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE)
        ) {
            statement.setLong(1, model.getId());
            statement.execute();
        } catch (SQLException | DAOException e) {
            logger.error("Cannot delete row: " + e);
            throw new DAOException("Cannot delete row", e);
        }
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
        feed.setJobInterval(resultSet.getInt("job_interval"));
        return feed;
    }

}
