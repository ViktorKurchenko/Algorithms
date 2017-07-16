package algorithms.sort.impl;

import algorithms.sort.AbstractSort;

import java.util.List;

/**
 * Insertion sort algorithm implementation
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSort<T> {

    private InsertionSort(List<T> list, Boolean ascendingOrder) {
        super(list, !ascendingOrder);
    }

    @Override
    protected InsertionSort<T> sort() {
        for (int i = 1; i < list.size(); i++) {
            T key = list.get(i);
            int j = i - 1;
            while ((j >= 0) && isElementInPlace(key, j)) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
        return this;
    }

}
