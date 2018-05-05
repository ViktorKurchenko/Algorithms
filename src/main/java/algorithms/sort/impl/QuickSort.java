package algorithms.sort.impl;

import algorithms.sort.AbstractSort;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Quick sort algorithm implementation
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSort<T> {

    private QuickSort(List<T> list, Boolean ascendingOrder) {
        super(list, !ascendingOrder);
    }

    @Override
    protected QuickSort<T> sort() {
        final List<T> sorted = quickSort(list);
        list.clear();
        list.addAll(sorted);
        return this;
    }

    private List<T> quickSort(List<T> list) {
        if (list.size() < 2) {
            return newArrayList(list);
        }
        final T pivot = list.get(0);
        final List<T> lower = newArrayList();
        final List<T> upper = newArrayList();
        for (int i = 1; i < list.size(); i++) {
            final T element = list.get(i);
            if (isElementLower(pivot, element)) {
                lower.add(element);
            } else {
                upper.add(element);
            }
        }
        final List<T> sorted = quickSort(lower);
        sorted.add(pivot);
        sorted.addAll(quickSort(upper));
        return sorted;
    }

    private boolean isElementLower(T pivot, T element) {
        return ascendingOrder
                ? pivot.compareTo(element) < 0
                : pivot.compareTo(element) > 0;
    }

}
