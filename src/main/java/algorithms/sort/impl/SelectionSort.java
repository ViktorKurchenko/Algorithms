package algorithms.sort.impl;

import algorithms.sort.AbstractSort;

import java.util.List;

/**
 * Selection sort algorithm implementation
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSort<T> {

    private SelectionSort(List<T> list, Boolean ascendingOrder) {
        super(list, ascendingOrder);
    }

    @Override
    protected SelectionSort<T> sort() {
        for (int i = 0; i < list.size() - 1; i++) {
            int keyPos = i;
            T key = list.get(i);
            for (int j = i + 1; j < list.size(); j++) {
                if (isKeyNotInPlace(key, j)) {
                    key = list.get(j);
                    keyPos = j;
                }
            }
            if (keyPos != i) {
                swap(i, keyPos, key);
            }
        }
        return this;
    }

    private void swap(int indexPos, int keyPos, T key) {
        T tmp = list.get(indexPos);
        list.set(indexPos, key);
        list.set(keyPos, tmp);
    }

}
