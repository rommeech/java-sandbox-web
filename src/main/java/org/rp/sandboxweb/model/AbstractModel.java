package org.rp.sandboxweb.model;

import java.io.Serializable;
import java.util.Objects;

public class AbstractModel<T> implements Serializable {

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

    public void setId(T id) {
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
