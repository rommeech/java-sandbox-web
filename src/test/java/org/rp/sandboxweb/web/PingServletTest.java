package org.rp.sandboxweb.web;

import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PingServletTest {

    PingServlet servlet;
    HttpServletRequest request;
    HttpServletResponse response;
    StringWriter stringWriter;
    PrintWriter printWriter;

    @Before
    public void setUp() throws IOException {
        servlet = new PingServlet();
        servlet.init();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);
    }

    @Test
    public void doGet() throws IOException {
        servlet.doGet(request, response);
        verify(response, times(1)).setStatus(HttpServletResponse.SC_OK);
        assertEquals("OK", stringWriter.toString().replace("\r", "").replace("\n", ""));
    }

    @Test
    public void doPost() throws IOException {
        servlet.doPost(request, response);
        verify(response, times(1)).setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}
