package algorithms.sort;

import java.util.List;

/**
 * Selection sort algorithm implementation
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSort<T> {

    SelectionSort(List<T> list, Boolean increasingOrder) {
        super(list, increasingOrder);
    }

    @Override
    protected void sort() {
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
                T tmp = list.get(i);
                list.set(i, key);
                list.set(keyPos, tmp);
            }
        }
    }

}
