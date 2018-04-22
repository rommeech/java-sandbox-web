package org.rp.sandboxweb.ui;

import javax.servlet.ServletException;

public class ExceptionUI extends ServletException {

    private static final long serialVersionUID = -2086675003283416513L;

    public ExceptionUI() {
        super();
    }

    public ExceptionUI(String message) {
        super(message);
    }

    public ExceptionUI(String message, Throwable cause) {
        super(message, cause);
    }
}
