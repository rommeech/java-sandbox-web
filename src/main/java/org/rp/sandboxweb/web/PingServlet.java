package org.rp.sandboxweb.web;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PingServlet extends HttpServlet {

    private static Logger logger;

    @Override
    public void init() {
        logger = LogManager.getLogger(PingServlet.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("doGet called");
        resp.setContentType("text/plain, charset=UTF-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        PrintWriter writer = resp.getWriter();

        InitialContext cxt;
        DataSource ds = null;
        try {
            cxt = new InitialContext();
            ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/DBDataSource");
        } catch (NamingException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        try {
            connection = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        writer.println("OK " + ds + " / " + connection);

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.error("doPost called");
        resp.setContentType("text/html, charset=UTF-8");
        resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        PrintWriter writer = resp.getWriter();
        writer.println("<h1>Method Not Allowed</h1>");
    }
}
