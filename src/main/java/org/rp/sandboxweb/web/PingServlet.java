package org.rp.sandboxweb.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PingServlet extends HttpServlet {

    private static Logger logger;

    @Override
    public void init() throws ServletException {
        logger = LogManager.getLogger(PingServlet.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("doGet called");
        resp.setContentType("text/plain, charset=UTF-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        PrintWriter writer = resp.getWriter();
        writer.println("OK");
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
