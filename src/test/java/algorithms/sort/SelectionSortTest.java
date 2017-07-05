package algorithms.sort;

import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.reverseOrder;
import static java.util.Comparator.naturalOrder;
import static org.assertj.core.api.Assertions.assertThat;

public class SelectionSortTest extends SortTestCase {

    @Test(expected = IllegalArgumentException.class)
    public void test_constructorThrowExceptionForNullArgument() {
        new SelectionSort<Integer>(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_constructorThrowExceptionForEmptyList() {
        new SelectionSort<Integer>(newArrayList());
    }

    @Test
    public void test_sortListWithOneItem() {
        AbstractSort<Integer> selectionSort = new SelectionSort<>(SINGLETON_LIST);

        assertThat(selectionSort.getSortedList()).isEqualTo(SINGLETON_LIST);
    }

    @Test
    public void test_successIncreaseSort() {
        AbstractSort<Integer> selectionSort = new SelectionSort<>(UNSORTED_LIST);

        assertThat(selectionSort.getSortedList()).isEqualTo(INCREASE_SORTED_LIST);
    }

    @Test
    public void test_successDecreaseSort() {
        AbstractSort<Integer> selectionSort = new SelectionSort<>(UNSORTED_LIST, false);

        assertThat(selectionSort.getSortedList()).isEqualTo(DECREASE_SORTED_LIST);
    }

    @Test
    public void test_successIncreaseSortForRandomGeneratedList() {
        final List<Integer> RANDOM_LIST = createRandomArray();

        AbstractSort<Integer> selectionSort = new SelectionSort<>(RANDOM_LIST);
        RANDOM_LIST.sort(naturalOrder());

        assertThat(selectionSort.getSortedList()).isEqualTo(RANDOM_LIST);
    }

    @Test
    public void test_successDecreaseSortForRandomGeneratedList() {
        final List<Integer> RANDOM_LIST = createRandomArray();

        AbstractSort<Integer> selectionSort = new SelectionSort<>(RANDOM_LIST, false);
        RANDOM_LIST.sort(reverseOrder());

        assertThat(selectionSort.getSortedList()).isEqualTo(RANDOM_LIST);
    }

}
