package algorithms.sort;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.nonNull;

/**
 * Insertion sort algorithm implementation
 */
public class InsertionSort<T extends Comparable<T>> {

    private final List<T> list;

    public InsertionSort(List<T> list) {
        checkArgument(nonNull(list) && !list.isEmpty(), "List is empty!");
        this.list = new ArrayList<>(list);
        sort();
    }

    public List<T> getSortedList() {
        return new ArrayList<>(list);
    }

    private void sort() {
        for (int i = 1; i < list.size(); i++) {
            T key = list.get(i);
            int j = i - 1;
            while ((j >= 0) && isKeyLess(key, j)) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    private boolean isKeyLess(T key, int index) {
        return key.compareTo(list.get(index)) < 0;
    }

}
