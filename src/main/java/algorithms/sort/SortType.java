package algorithms.sort;

import algorithms.sort.impl.BubbleSort;
import algorithms.sort.impl.InsertionSort;
import algorithms.sort.impl.MergeSort;
import algorithms.sort.impl.SelectionSort;

public enum SortType {

    BUBBLE_SORT(BubbleSort.class), SELECTION_SORT(SelectionSort.class), INSERTION_SORT(InsertionSort.class),
    MERGE_SORT(MergeSort.class);

    private final Class<? extends AbstractSort> type;

    SortType(Class<? extends AbstractSort> type) {
        this.type = type;
    }

    public Class<? extends AbstractSort> getType() {
        return type;
    }

}
