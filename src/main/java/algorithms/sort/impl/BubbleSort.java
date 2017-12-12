package algorithms.sort.impl;

import algorithms.sort.AbstractSort;

import java.util.List;

/**
 * Bubble sort algorithm implementation
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSort<T> {

    private BubbleSort(List<T> list, Boolean ascendingOrder) {
        super(list, ascendingOrder);
    }

    @Override
    protected BubbleSort<T> sort() {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (isElementNotInPlace(list.get(i), j)) {
                    swap(i, j);
                }
            }
        }
        return this;
    }

    private void swap(int i, int j) {
        T tmp = list.get(j);
        list.set(j, list.get(i));
        list.set(i, tmp);
    }

}
