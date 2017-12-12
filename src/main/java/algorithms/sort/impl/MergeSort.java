package algorithms.sort.impl;

import algorithms.sort.AbstractSort;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Merge sort algorithm implementation
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSort<T> {

    private MergeSort(List<T> list, Boolean ascendingOrder) {
        super(list, !ascendingOrder);
    }

    @Override
    protected MergeSort<T> sort() {
        mergeSort(list);
        return this;
    }

    private void mergeSort(List<T> list) {
        if (list.size() > 1) {
            final int MIDDLE = list.size() / 2;
            List<T> left = list.subList(0, MIDDLE);
            List<T> right = list.subList(MIDDLE, list.size());
            mergeSort(left);
            mergeSort(right);
            merge(left, right);
        }
    }

    private void merge(List<T> left, List<T> right) {
        List<T> result = newArrayList();
        int leftIndex = 0;
        int rightIndex = 0;
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (isElementInPlace(left.get(leftIndex), right.get(rightIndex))) {
                result.add(left.get(leftIndex));
                leftIndex++;
            } else {
                result.add(right.get(rightIndex));
                rightIndex++;
            }
        }
        fillResult(left, leftIndex, result);
        fillResult(right, rightIndex, result);
        fillListsFromResult(left, right, result);
    }

    private void fillResult(List<T> left, int leftIndex, List<T> result) {
        for (; leftIndex < left.size(); leftIndex++) {
            result.add(left.get(leftIndex));
        }
    }

    private void fillListsFromResult(List<T> left, List<T> right, List<T> result) {
        int index = 0;
        for (int i = 0; i < left.size(); i++, index++) {
            left.set(i, result.get(index));
        }
        for (int i = 0; i < right.size(); i++, index++) {
            right.set(i, result.get(index));
        }
    }

    private boolean isElementInPlace(T left, T right) {
        return ascendingOrder ?
                left.compareTo(right) > 0 :
                left.compareTo(right) < 0;
    }

}
