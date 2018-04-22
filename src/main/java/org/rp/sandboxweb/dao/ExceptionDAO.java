package org.rp.sandboxweb.dao;

public class ExceptionDAO extends Exception {

    private static final long serialVersionUID = -8110551667446361597L;

    public ExceptionDAO() {
        super();
    }

    public ExceptionDAO(String message) {
        super(message);
    }

    public ExceptionDAO(String message, Throwable cause) {
        super(message, cause);
    }

}
