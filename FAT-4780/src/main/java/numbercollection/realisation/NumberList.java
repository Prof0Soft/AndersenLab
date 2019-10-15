package numbercollection.realisation;

import numbercollection.NumberListCollection;

import java.util.AbstractList;
import java.util.Objects;

/**
 * Collections for use only Numeric variables.
 *
 * @param <E> Numeric and downstream classes.
 */
public class NumberList<E extends Number> extends AbstractList<E> implements NumberListCollection<E> {

    /**
     * Default collection capacity.
     */
    private final int DEFAULT_CAPACITY = 4;
    /**
     * Size incre
     */
    private final int CUR_RATE = 4;

    /**
     * Data storage.
     */
    private Object[] elementData;

    /**
     * Size of data storage.
     */
    private int size;

    /**
     * Default constructor.
     */
    public NumberList() {
        this.elementData = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Constructor with own list capacity.
     *
     * @param initialCapacity own capacity for list.
     */
    public NumberList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Wrong Capacity " + initialCapacity);
        }
    }

    /**
     * Add new element for data storage.
     * @param e adding element.
     * @return true if element was added.
     */
    @Override
    public boolean add(E e) {
        resizeElementData();
        if (elementData.length > size) {
            elementData[size++] = e;
            return true;
        }

        return false;
    }

    /**
     * Resize data storage.
     */
    private void resizeElementData() {
        int newSize = elementData.length + CUR_RATE;

        if (elementData.length < (size + CUR_RATE + 1)) {
            Object[] newData = new Object[newSize];
            System.arraycopy(elementData, 0, newData, 0, elementData.length);
            elementData = newData;
        }
    }

    /**
     * Size for busy data cells of list.
     * @return count element in list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Give element object by id.
     * @param index searching object's index.
     * @return object by index.
     */
    @Override
    public E get(int index) {
        if (index >= 0 && index < size) {
            return (E) elementData[index];
        } else {
            throw new IllegalArgumentException("Index of bounds " + index);
        }
    }

    /**
     * Remove element by id.
     *
     * @param index the id for delete element.
     * @return deleted object.
     */
    @Override
    public E remove(int index) {
        Objects.checkIndex(index, size);
        final Object[] es = elementData;

        @SuppressWarnings("unchecked") E oldValue = (E) es[index];
        fastRemove(es, index);

        return oldValue;
    }

    /**
     * Private remove method that skips bounds checking and does not
     * return the value removed.
     */
    private void fastRemove(Object[] es, int i) {
        final int newSize;
        if ((newSize = size - 1) > i)
            System.arraycopy(es, i + 1, es, i, newSize - i);
        es[size = newSize] = null;
    }

    /**
     * Check data storage for empty.
     * @return true if data don't contain objects.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Search in data storage object
     * @param o object for searching.
     * @return true if data storage contain the object.
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * Searching index in data storage by object.
     * @param o object of searching index.
     * @return index by searching object.
     */
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Searching maximum element by data storage.
     * @return maximum value.
     */
    @Override
    public E max() {
        E maxVal = get(0);
        for (int i = 1; i < size(); i++) {
            if (elementData[i] instanceof Integer) {
                if ((Integer) elementData[i] > (Integer) maxVal) {
                    maxVal = get(i);
                }
            }
            if (elementData[i] instanceof Double) {
                if ((Double) elementData[i] > (Double) maxVal) {
                    maxVal = get(i);
                }
            }
            if (elementData[i] instanceof Long) {
                if ((Long) elementData[i] > (Long) maxVal) {
                    maxVal = get(i);
                }
            }
        }
        return maxVal;
    }

    /**
     * Searching minimum element by data storage.
     * @return minimum value.
     */
    @Override
    public E min() {
        E minVal = get(0);
        for (int i = 1; i < size(); i++) {
            if (elementData[i] instanceof Integer) {
                if ((Integer) elementData[i] < (Integer) minVal) {
                    minVal = get(i);
                }
            }
            if (elementData[i] instanceof Double) {
                if ((Double) elementData[i] < (Double) minVal) {
                    minVal = get(i);
                }
            }
            if (elementData[i] instanceof Long) {
                if ((Long) elementData[i] < (Long) minVal) {
                    minVal = get(i);
                }
            }
        }
        return minVal;
    }

    /**
     * Count average for contains data list.
     * @return average count.
     */
    @Override
    public E average() {
        if (elementData[0] instanceof Integer) {
            return (E) averageInteger();
        } else if (elementData[0] instanceof Double) {
            return (E) averageDouble();
        } else if (elementData[0] instanceof Long) {
            return (E) averageLong();
        }
        return null;
    }

    private Integer averageInteger() {
        Integer sum = 0;

        for (int i = 0; i < size; i++) {
            sum = sum + (Integer) elementData[i];
        }
        return sum / size;
    }

    private Double averageDouble() {
        Double sum = 0D;

        for (int i = 0; i < size; i++) {
            sum = sum + (Double) elementData[i];
        }
        return sum / size;
    }

    private Long averageLong() {
        Long sum = 0L;

        for (int i = 0; i < size; i++) {
            sum = sum + (Long) elementData[i];
        }
        return sum / size;
    }
}
