package algorithms.sort.impl;

import algorithms.search.BinarySearch;
import algorithms.sort.AbstractSort;

import java.util.List;

/**
 * Insertion sort algorithm implementation
 */
public class InsertionBinarySort<T extends Comparable<T>> extends AbstractSort<T> {

    private final BinarySearch<T> binarySearch;

    private InsertionBinarySort(List<T> list, Boolean ascendingOrder) {
        super(list, !ascendingOrder);
        binarySearch = new BinarySearch<>(list);
    }

    @Override
    protected InsertionBinarySort<T> sort() {
        for (int i = 1; i < list.size(); i++) {
            T key = list.get(i);
            int j = i - 1;
            if (isElementNotInPlace(key, j)) {

            }


            while ((j >= 0) && isElementNotInPlace(key, j)) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
        return this;
    }

}
