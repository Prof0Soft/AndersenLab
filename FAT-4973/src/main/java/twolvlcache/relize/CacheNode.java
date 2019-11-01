package twolvlcache.relize;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * The wrap class which store object in cache.
 */
@Data
public class CacheNode<Key, Value> implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Key key;
    private Value value;
    private Long timeAccess;

    public CacheNode(final Key key, final Value value) {
        this.key = key;
        this.value = value;
        this.timeAccess = System.currentTimeMillis();
    }

    public void updateData(final Value value) {
        this.value = value;
        this.timeAccess = System.currentTimeMillis();
    }

    public Value getValue() {
        this.timeAccess = System.currentTimeMillis();
        return value;
    }

    public Long getTimeAccess() {
        return timeAccess;
    }

    @Override
    public String toString() {
        return "key=" + key
                + ", value=" + value
                + ", timeAccess=" + timeAccess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CacheNode<?, ?> that = (CacheNode<?, ?>) o;
        return Objects.equals(key, that.key) &&
                Objects.equals(value, that.value) &&
                Objects.equals(timeAccess, that.timeAccess);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value, timeAccess);
    }
}
