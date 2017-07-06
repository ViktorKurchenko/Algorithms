package algorithms.sort;

public enum SortType {

    BUBBLE_SORT(BubbleSort.class), SELECTION_SORT(SelectionSort.class), INSERTION_SORT(InsertionSort.class);

    private final Class type;

    SortType(Class type) {
        this.type = type;
    }

    public Class getType() {
        return type;
    }

}
