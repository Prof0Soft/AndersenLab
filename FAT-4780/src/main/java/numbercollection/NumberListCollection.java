package numbercollection;

import java.util.Collection;

public interface NumberListCollection<E> extends Collection<E> {
    /**
     * Return max value od list.
     *
     * @return E object.
     */
    E max();

    /**
     * Return min value of list.
     * @return E object.
     */
    E min();

    /**
     * Return average value of list.
     * @return E object.
     */
    E average();

    /**
     * Get list element by id.
     * @return object by element.
     */
    E get(int index);

    /**
     * Search index in list by Object.
     * @param o object of searching index.
     * @return if object is contain return index, else return -1.
     */
    int indexOf(Object o);

    E remove(int index);
}
