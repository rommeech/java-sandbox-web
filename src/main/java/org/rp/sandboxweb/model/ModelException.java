package org.rp.sandboxweb.model;

public class ModelException extends Exception {

    private static final long serialVersionUID = 7284374320120938338L;

    public ModelException() {
        super();
    }

    public ModelException(String message) {
        super(message);
    }

    public ModelException(String message, Throwable cause) {
        super(message, cause);
    }

}
