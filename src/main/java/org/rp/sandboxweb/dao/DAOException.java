package org.rp.sandboxweb.dao;

public class DAOException extends Exception {

    private static final long serialVersionUID = -8110551667446361597L;

    public DAOException() {
        super();
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

}
