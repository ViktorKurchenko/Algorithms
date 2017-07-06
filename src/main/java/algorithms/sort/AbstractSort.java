package algorithms.sort;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Contains common sort methods
 */
public abstract class AbstractSort<T extends Comparable<T>> {

    final List<T> list;
    private final boolean increasingOrder;


    AbstractSort(@NotNull List<T> list, boolean increasingOrder) {
        this.list = newArrayList(list);
        this.increasingOrder = increasingOrder;
    }

    public List<T> getSortedList() {
        return newArrayList(list);
    }

    protected abstract void sort();

    boolean isKeyNotInPlace(T key, int index) {
        return increasingOrder ?
                key.compareTo(list.get(index)) > 0 :
                key.compareTo(list.get(index)) < 0;
    }

}
