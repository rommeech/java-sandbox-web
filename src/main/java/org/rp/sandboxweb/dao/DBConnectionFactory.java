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

    public static Connection getConnection() throws ExceptionDAO {

        Context initCtx;
        Context envCtx;
        DataSource ds;
        Connection connection = null;

        try {
            initCtx = new InitialContext();
            envCtx = (Context) initCtx.lookup("java:/comp/env");
            ds = (DataSource) envCtx.lookup("jdbc/DBDataSource");

            if (ds != null) {
                connection = ds.getConnection();
            }

            initCtx.close();
        } catch (NamingException | SQLException e) {
            logger.fatal("Cannot connect to db; " + e.getMessage());
            throw new ExceptionDAO("Cannot connect to db; " + e.getMessage());
        }

        return connection;
    }
}
