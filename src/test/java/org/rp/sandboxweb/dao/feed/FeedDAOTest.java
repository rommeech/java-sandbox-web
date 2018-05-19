package org.rp.sandboxweb.dao.feed;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.rp.sandboxweb.dao.DAOException;
import org.rp.sandboxweb.dao.MockInitialContextFactory;
import org.rp.sandboxweb.model.Status;
import org.rp.sandboxweb.model.feed.Feed;

import javax.naming.Context;
import javax.sql.DataSource;

import java.sql.*;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class FeedDAOTest {

    DataSource dataSource;
    Connection connection;
    PreparedStatement statement;
    ResultSet resultSet;
    ResultSet keySet;
    Feed feed;
    Date date;

    @BeforeClass
    public static void setUpClass() {
        System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.rp.sandboxweb.dao.MockInitialContextFactory");
    }

    @Before
    public void setUp() throws Exception {
        date = new Date(System.currentTimeMillis());

        dataSource = mock(DataSource.class);
        connection = mock(Connection.class);
        statement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);
        keySet = mock(ResultSet.class);

        feed = new Feed();
        feed.setId(3L);
        feed.setStatus(Status.NEW);
        feed.setFeedUrl("URL3");
        feed.setLogoUrl("LOGO3");
        feed.setTitle("TITLE3");
        feed.setDescription("D3");
        feed.setAuthor("A3");
        feed.setNextJob(date);
        feed.setJobInterval(300);

        when(resultSet.getLong("id")).thenReturn(1L).thenReturn(2L);
        when(resultSet.getString("status")).thenReturn("ACTIVE").thenReturn("DISABLED");
        when(resultSet.getString("feed_url")).thenReturn("FEED_URL1").thenReturn("FEED_URL2");
        when(resultSet.getString("logo_url")).thenReturn("LOGO_URL1").thenReturn("LOGO_URL2");
        when(resultSet.getString("title")).thenReturn("TITLE1").thenReturn("TITLE2");
        when(resultSet.getString("description")).thenReturn("DESCRIPTION1").thenReturn("DESCRIPTION2");
        when(resultSet.getString("author")).thenReturn("AUTHOR1").thenReturn("AUTHOR2");
        when(resultSet.getDate("next_job")).thenReturn(date);
        when(resultSet.getInt("job_interval")).thenReturn(100).thenReturn(200);

        when(keySet.next()).thenReturn(true);
        when(keySet.getLong(1)).thenReturn(3L);
        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(statement.getGeneratedKeys()).thenReturn(keySet);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);

        MockInitialContextFactory.setContext(dataSource);
    }

    @Test
    public void getById() throws DAOException, SQLException {

        FeedDAO feedDAO = new FeedDAO();
        Feed feed = feedDAO.getById(1L);

        verify(statement, times(1)).setLong(1, 1L);

        assertNotNull("Feed is not null", feed);
        assertEquals("ID", 1L, feed.getId().longValue());
        assertEquals("Status", Status.ACTIVE, feed.getStatus());
        assertEquals("FeedUrl", "FEED_URL1", feed.getFeedUrl());
        assertEquals("LogoUrl", "LOGO_URL1", feed.getLogoUrl());
        assertEquals("Title", "TITLE1", feed.getTitle());
        assertEquals("Description", "DESCRIPTION1", feed.getDescription());
        assertEquals("Author", "AUTHOR1", feed.getAuthor());
        assertEquals("NextJob", date, feed.getNextJob());
        assertEquals("JobInterval", 100, feed.getJobInterval());

    }

    @Test
    public void getAll() throws DAOException {

        FeedDAO feedDAO = new FeedDAO();
        List<Feed> feeds = feedDAO.getAll();

        assertEquals("getAll rows", 2, feeds.size());

        assertEquals("ID", 1L, feeds.get(0).getId().longValue());
        assertEquals("FeedUrl", "FEED_URL1", feeds.get(0).getFeedUrl());
        assertEquals("LogoUrl", "LOGO_URL1", feeds.get(0).getLogoUrl());
        assertEquals("Title", "TITLE1", feeds.get(0).getTitle());
        assertEquals("Description", "DESCRIPTION1", feeds.get(0).getDescription());
        assertEquals("Author", "AUTHOR1", feeds.get(0).getAuthor());
        assertEquals("NextJob", date, feeds.get(0).getNextJob());
        assertEquals("JobInterval", 100, feeds.get(0).getJobInterval());

        assertEquals("ID", 2L, feeds.get(1).getId().longValue());
        assertEquals("FeedUrl", "FEED_URL2", feeds.get(1).getFeedUrl());
        assertEquals("LogoUrl", "LOGO_URL2", feeds.get(1).getLogoUrl());
        assertEquals("Title", "TITLE2", feeds.get(1).getTitle());
        assertEquals("Description", "DESCRIPTION2", feeds.get(1).getDescription());
        assertEquals("Author", "AUTHOR2", feeds.get(1).getAuthor());
        assertEquals("NextJob", date, feeds.get(1).getNextJob());
        assertEquals("JobInterval", 200, feeds.get(1).getJobInterval());
    }

    @Test
    public void insert() throws DAOException, SQLException {
        FeedDAO feedDAO = new FeedDAO();
        feedDAO.insert(feed);
        verify(statement, times(1)).setString(1, feed.getFeedUrl());
        verify(statement, times(1)).setString(2, feed.getLogoUrl());
        verify(statement, times(1)).setString(3, feed.getTitle());
        verify(statement, times(1)).setString(4, feed.getDescription());
        verify(statement, times(1)).setString(5, feed.getStatus().name());
        verify(statement, times(1)).setString(6, feed.getAuthor());
        verify(statement, times(1)).setDate(7, feed.getNextJob());
        verify(statement, times(1)).setInt(8, feed.getJobInterval());
        verify(statement, times(1)).execute();
    }


    @Test
    public void update() throws DAOException, SQLException {
        FeedDAO feedDAO = new FeedDAO();
        feedDAO.update(feed);
        verify(statement, times(1)).setString(1, feed.getFeedUrl());
        verify(statement, times(1)).setString(2, feed.getLogoUrl());
        verify(statement, times(1)).setString(3, feed.getTitle());
        verify(statement, times(1)).setString(4, feed.getDescription());
        verify(statement, times(1)).setString(5, feed.getStatus().name());
        verify(statement, times(1)).setString(6, feed.getAuthor());
        verify(statement, times(1)).setDate(7, feed.getNextJob());
        verify(statement, times(1)).setInt(8, feed.getJobInterval());
        verify(statement, times(1)).setLong(9, feed.getId());
        verify(statement, times(1)).execute();
    }

    @Test
    public void delete() throws DAOException, SQLException {
        FeedDAO feedDAO = new FeedDAO();
        feedDAO.delete(feed);
        verify(statement, times(1)).setLong(1, feed.getId());
        verify(statement, times(1)).execute();
    }
}