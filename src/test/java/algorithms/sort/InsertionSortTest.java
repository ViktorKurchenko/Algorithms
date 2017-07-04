package algorithms.sort;

import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.primitives.Ints.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class InsertionSortTest {

    private static final List<Integer> SINGLETON_LIST = singletonList(1);
    private static final List<Integer> SORTED_LIST = asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    private static final List<Integer> UNSORTED_LIST = asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0);

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
    public void test_successSort() {
        InsertionSort<Integer> insertionSort = new InsertionSort<>(UNSORTED_LIST);

        assertThat(insertionSort.getSortedList()).isEqualTo(SORTED_LIST);
    }

    @Test
    public void test_successSortForRandomGeneratedList() {
        final List<Integer> RANDOM_LIST = createRandomArray(1_000);

        InsertionSort<Integer> insertionSort = new InsertionSort<>(RANDOM_LIST);
        Collections.sort(RANDOM_LIST);

        assertThat(insertionSort.getSortedList()).isEqualTo(RANDOM_LIST);
    }

    private List<Integer> createRandomArray(int size) {
        final List<Integer> list = newArrayList();
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt());
        }
        return list;
    }

}
