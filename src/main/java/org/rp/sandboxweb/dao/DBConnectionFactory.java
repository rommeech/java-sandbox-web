package org.rp.sandboxweb.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionFactory {

    private static Logger logger = LogManager.getLogger(DBConnectionFactory.class);

    public static Connection getConnection() throws DAOException {

        Context initCtx = null;
        DataSource ds;
        Connection connection = null;

        try {
            initCtx = new InitialContext();
            ds = (DataSource) initCtx.lookup("java:/comp/env/jdbc/DBDataSource");

            if (ds != null) {
                connection = ds.getConnection();
            }
        } catch (NamingException | SQLException e) {
            logger.fatal("Cannot connect to db; " + e.getMessage());
            throw new DAOException("Cannot connect to db; " + e.getMessage());
        }
        finally {
            if (initCtx != null) {
                try {
                    initCtx.close();
                } catch (NamingException e) {
                    e.printStackTrace();
                }
            }
        }

        return connection;
    }
}
