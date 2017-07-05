package algorithms.sort;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.nonNull;

/**
 * Contains common sort methods
 */
public abstract class AbstractSort<T extends Comparable<T>> {

    protected final List<T> list;
    protected final boolean increasingOrder;


    public AbstractSort(List<T> list, boolean increasingOrder) {
        checkArgument(nonNull(list) && !list.isEmpty(), "List is empty!");
        this.list = new ArrayList<>(list);
        this.increasingOrder = increasingOrder;
        sort();
    }

    public List<T> getSortedList() {
        return new ArrayList<>(list);
    }

    protected abstract void sort();

    protected boolean isKeyNotInPlace(T key, int index) {
        return increasingOrder ?
                key.compareTo(list.get(index)) > 0 :
                key.compareTo(list.get(index)) < 0;
    }

}
