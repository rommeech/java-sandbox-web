package org.rp.sandboxweb.ui;

import javax.servlet.ServletException;

public class UIException extends ServletException {

    private static final long serialVersionUID = -2086675003283416513L;

    public UIException() {
        super();
    }

    public UIException(String message) {
        super(message);
    }

    public UIException(String message, Throwable cause) {
        super(message, cause);
    }
}
