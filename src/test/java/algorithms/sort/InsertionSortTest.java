package algorithms.sort;

import org.junit.Test;

import java.util.List;
import java.util.Random;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.primitives.Ints.asList;
import static java.util.Collections.reverseOrder;
import static java.util.Collections.singletonList;
import static java.util.Comparator.naturalOrder;
import static org.assertj.core.api.Assertions.assertThat;

public class InsertionSortTest {

    private static final List<Integer> SINGLETON_LIST = singletonList(1);
    private static final List<Integer> INCREASE_SORTED_LIST = asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    private static final List<Integer> DECREASE_SORTED_LIST = asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
    private static final List<Integer> UNSORTED_LIST = asList(1, 9, 2, 8, 3, 7, 4, 6, 5, 10, 0);

    private static final int RANDOM_LIST_SIZE = 1_000;
    private static final Random random = new Random();


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
        InsertionSort<Integer> insertionSort = new InsertionSort<>(SINGLETON_LIST);

        assertThat(insertionSort.getSortedList()).isEqualTo(SINGLETON_LIST);
    }

    @Test
    public void test_successIncreaseSort() {
        InsertionSort<Integer> insertionSort = new InsertionSort<>(UNSORTED_LIST);

        assertThat(insertionSort.getSortedList()).isEqualTo(INCREASE_SORTED_LIST);
    }

    @Test
    public void test_successDecreaseSort() {
        InsertionSort<Integer> insertionSort = new InsertionSort<>(UNSORTED_LIST, false);

        assertThat(insertionSort.getSortedList()).isEqualTo(DECREASE_SORTED_LIST);
    }

    @Test
    public void test_successIncreaseSortForRandomGeneratedList() {
        final List<Integer> RANDOM_LIST = createRandomArray();

        InsertionSort<Integer> insertionSort = new InsertionSort<>(RANDOM_LIST);
        RANDOM_LIST.sort(naturalOrder());

        assertThat(insertionSort.getSortedList()).isEqualTo(RANDOM_LIST);
    }

    @Test
    public void test_successDecreaseSortForRandomGeneratedList() {
        final List<Integer> RANDOM_LIST = createRandomArray();

        InsertionSort<Integer> insertionSort = new InsertionSort<>(RANDOM_LIST, false);
        RANDOM_LIST.sort(reverseOrder());

        assertThat(insertionSort.getSortedList()).isEqualTo(RANDOM_LIST);
    }

    private List<Integer> createRandomArray() {
        final List<Integer> list = newArrayList();
        for (int i = 0; i < RANDOM_LIST_SIZE; i++) {
            list.add(random.nextInt());
        }
        return list;
    }

}
