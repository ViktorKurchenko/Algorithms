package algorithms.sort;

import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.reverseOrder;
import static java.util.Comparator.naturalOrder;
import static org.assertj.core.api.Assertions.assertThat;

public class InsertionSortTest extends SortTestCase {

    @Test(expected = IllegalArgumentException.class)
    public void test_constructorThrowExceptionForNullArgument() {
        new InsertionSort<Integer>(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_constructorThrowExceptionForEmptyList() {
        new InsertionSort<Integer>(newArrayList());
    }

    @Test
    public void test_sortListWithOneItem() {
        AbstractSort<Integer> insertionSort = new InsertionSort<>(SINGLETON_LIST);

        assertThat(insertionSort.getSortedList()).isEqualTo(SINGLETON_LIST);
    }

    @Test
    public void test_successIncreaseSort() {
        AbstractSort<Integer> insertionSort = new InsertionSort<>(UNSORTED_LIST);

        assertThat(insertionSort.getSortedList()).isEqualTo(INCREASE_SORTED_LIST);
    }

    @Test
    public void test_successDecreaseSort() {
        AbstractSort<Integer> insertionSort = new InsertionSort<>(UNSORTED_LIST, false);

        assertThat(insertionSort.getSortedList()).isEqualTo(DECREASE_SORTED_LIST);
    }

    @Test
    public void test_successIncreaseSortForRandomGeneratedList() {
        final List<Integer> RANDOM_LIST = createRandomArray();

        AbstractSort<Integer> insertionSort = new InsertionSort<>(RANDOM_LIST);
        RANDOM_LIST.sort(naturalOrder());

        assertThat(insertionSort.getSortedList()).isEqualTo(RANDOM_LIST);
    }

    @Test
    public void test_successDecreaseSortForRandomGeneratedList() {
        final List<Integer> RANDOM_LIST = createRandomArray();

        AbstractSort<Integer> insertionSort = new InsertionSort<>(RANDOM_LIST, false);
        RANDOM_LIST.sort(reverseOrder());

        assertThat(insertionSort.getSortedList()).isEqualTo(RANDOM_LIST);
    }

}
