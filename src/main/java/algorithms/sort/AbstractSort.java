package algorithms.sort;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Contains common sort methods
 */
public abstract class AbstractSort<T extends Comparable<T>> {

    protected final List<T> list;
    protected final boolean ascendingOrder;


    protected AbstractSort(@NotNull List<T> list, boolean ascendingOrder) {
        this.list = newArrayList(list);
        this.ascendingOrder = ascendingOrder;
    }

    public List<T> getSortedList() {
        return newArrayList(list);
    }

    protected abstract AbstractSort<T> sort();

    protected boolean isElementNotInPlace(T key, int index) {
        return ascendingOrder
                ? key.compareTo(list.get(index)) > 0
                : key.compareTo(list.get(index)) < 0;
    }

}
