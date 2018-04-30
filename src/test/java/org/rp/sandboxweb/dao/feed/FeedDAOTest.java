package org.rp.sandboxweb.dao.feed;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.rp.sandboxweb.dao.DAOException;
import org.rp.sandboxweb.dao.MockInitialContextFactory;
import org.rp.sandboxweb.model.Status;
import org.rp.sandboxweb.model.feed.Feed;

import javax.naming.Context;
import javax.sql.DataSource;

import java.sql.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;

public class FeedDAOTest {

    DataSource dataSource;
    Connection connection;
    PreparedStatement statement;
    ResultSet resultSet;
    Date date;

    @BeforeClass
    public static void setUpClass() {
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.rp.sandboxweb.dao.MockInitialContextFactory");
    }

    @Before
    public void setUp() throws Exception {
        dataSource = Mockito.mock(DataSource.class);
        connection = Mockito.mock(Connection.class);
        statement = Mockito.mock(PreparedStatement.class);
        resultSet = Mockito.mock(ResultSet.class);

        date = new Date(System.currentTimeMillis());

        Mockito.when(dataSource.getConnection()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(anyString())).thenReturn(statement);
        Mockito.when(statement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);

        MockInitialContextFactory.setContext(dataSource);
    }

    @Test
    public void getById() throws DAOException, SQLException {

        Mockito.when(resultSet.getString("status")).thenReturn("ACTIVE");
        Mockito.when(resultSet.getLong("id")).thenReturn(1L);
        Mockito.when(resultSet.getString("feed_url")).thenReturn("FEED_URL");
        Mockito.when(resultSet.getString("logo_url")).thenReturn("LOGO_URL");
        Mockito.when(resultSet.getString("title")).thenReturn("TITLE");
        Mockito.when(resultSet.getString("description")).thenReturn("DESCRIPTION");
        Mockito.when(resultSet.getString("author")).thenReturn("AUTHOR");
        Mockito.when(resultSet.getDate("next_job")).thenReturn(date);
        Mockito.when(resultSet.getLong("job_interval")).thenReturn(100L);

        FeedDAO feedDAO = new FeedDAO();
        Feed feed = feedDAO.getById(1L);

        assertNotNull("Feed is not null", feed);
        assertEquals("ID", 1L, feed.getId().longValue());
        assertEquals("FeedUrl", "FEED_URL", feed.getFeedUrl());
        assertEquals("LogoUrl", "LOGO_URL", feed.getLogoUrl());
        assertEquals("Title", "TITLE", feed.getTitle());
        assertEquals("Description", "DESCRIPTION", feed.getDescription());
        assertEquals("Author", "AUTHOR", feed.getAuthor());
        assertEquals("NextJob", date, feed.getNextJob());
        assertEquals("JobInterval", 100L, feed.getJobInterval().longValue());

    }

    /*@Test
    public void getAll() {
        assertTrue(false);
    }

    @Test
    public void insert() {
        assertTrue(false);
    }

    @Test
    public void update() {
        assertTrue(false);
    }

    @Test
    public void delete() {
        assertTrue(false);
    }
    */
}