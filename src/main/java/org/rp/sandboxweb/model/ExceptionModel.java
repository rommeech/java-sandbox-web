package org.rp.sandboxweb.model;

public class ExceptionModel extends Exception {

    private static final long serialVersionUID = 7284374320120938338L;

    public ExceptionModel() {
        super();
    }

    public ExceptionModel(String message) {
        super(message);
    }

    public ExceptionModel(String message, Throwable cause) {
        super(message, cause);
    }

}
