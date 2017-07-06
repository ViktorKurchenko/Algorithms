package algorithms.sort.impl;

import algorithms.sort.AbstractSort;

import java.util.List;

/**
 * Bubble sort algorithm implementation
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSort<T> {

    BubbleSort(List<T> list, Boolean increasingOrder) {
        super(list, increasingOrder);
    }

    @Override
    protected BubbleSort<T> sort() {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (isKeyNotInPlace(list.get(i), j)) {
                    T tmp = list.get(j);
                    list.set(j, list.get(i));
                    list.set(i, tmp);
                }
            }
        }
        return this;
    }

}