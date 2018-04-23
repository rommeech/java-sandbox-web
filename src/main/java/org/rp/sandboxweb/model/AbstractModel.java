package org.rp.sandboxweb.model;

import com.sun.tools.internal.ws.processor.model.ModelException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.util.Objects;

public abstract class AbstractModel<T> implements Serializable {

    private static Logger logger = LogManager.getLogger(AbstractModel.class);
    private static final long serialVersionUID = -5715298230785711619L;

    private T id;

    public AbstractModel() {
    }

    public AbstractModel(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }

    public void setId(T id) throws ExceptionModel {
        if (this.id != null && !id.equals(this.id)) {
            logger.error("Try to change Model ID");
            throw new ModelException("Cannot change ID!");
        }
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractModel)) return false;
        AbstractModel<?> that = (AbstractModel<?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AbstractModel{" +
                "id=" + id +
                '}';
    }
}
