package algorithms.sort.impl;

import algorithms.sort.AbstractSort;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.primitives.Ints.asList;

/**
 * Merge sort algorithm implementation
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSort<T> {

    private MergeSort(List<T> list, Boolean ascendingOrder) {
        super(list, !ascendingOrder);
    }

    public static void main(String[] args) {
        MergeSort<Integer> mergeSort = new MergeSort<>(asList(9, 0, 5, 3), true);
        mergeSort.sort();
        System.out.println(mergeSort.getSortedList());
    }

    @Override
    protected MergeSort<T> sort() {
        mergeSort(0, list.size() - 1);
        return this;
    }

    private void mergeSort(int start, int end) {
        if (start < end) {
            int middle = (start + end) / 2;
            mergeSort(start, middle);
            mergeSort(middle + 1, end);
            merge(start, middle, end);
        }
    }

    private void merge(int start, int middle, int end) {
        int n1 = middle - start + 1;
        int n2 = end - middle;
        List<T> L = newArrayList();
        List<T> R = newArrayList();
        for (int i = 0; i < n1; i++) {
            L.add(list.get(start + i));
        }
        for (int i = 0; i < n2; i++) {
            R.add(list.get(middle + i + 1));
        }
        System.out.println("L: " + L + "\tR: " + R);
        for (int i = 0, j = 0, k = start; k < end; k ++) {
            if (L.get(i).compareTo(R.get(j)) <= 0) {
                list.set(k, L.get(i));
                i++;
            } else if (list.get(k).compareTo(R.get(j)) == 0) {
                j++;
            }
        }
    }

}
