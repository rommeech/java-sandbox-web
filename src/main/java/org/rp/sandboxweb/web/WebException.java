package org.rp.sandboxweb.web;

import javax.servlet.ServletException;

public class WebException extends ServletException {

    private static final long serialVersionUID = -2086675003283416513L;

    public WebException(String message) { super(message); }
}
