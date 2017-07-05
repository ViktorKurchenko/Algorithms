package algorithms.sort;

import java.util.List;

/**
 * Insertion sort algorithm implementation
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSort<T> {

    public InsertionSort(List<T> list) {
        this(list, true);
    }

    public InsertionSort(List<T> list, boolean increasingOrder) {
        super(list, !increasingOrder);
    }

    @Override
    protected void sort() {
        for (int i = 1; i < list.size(); i++) {
            T key = list.get(i);
            int j = i - 1;
            while ((j >= 0) && isKeyNotInPlace(key, j)) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

}
