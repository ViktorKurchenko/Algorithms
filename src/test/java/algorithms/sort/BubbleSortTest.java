package algorithms.sort;

import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.reverseOrder;
import static java.util.Comparator.naturalOrder;
import static org.assertj.core.api.Assertions.assertThat;

public class BubbleSortTest extends SortTestCase {

    @Test(expected = IllegalArgumentException.class)
    public void test_constructorThrowExceptionForNullArgument() {
        new BubbleSort<Integer>(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_constructorThrowExceptionForEmptyList() {
        new BubbleSort<Integer>(newArrayList());
    }

    @Test
    public void test_sortListWithOneItem() {
        AbstractSort<Integer> bubbleSort = new BubbleSort<>(SINGLETON_LIST);

        assertThat(bubbleSort.getSortedList()).isEqualTo(SINGLETON_LIST);
    }

    @Test
    public void test_successIncreaseSort() {
        AbstractSort<Integer> bubbleSort = new BubbleSort<>(UNSORTED_LIST);

        assertThat(bubbleSort.getSortedList()).isEqualTo(INCREASE_SORTED_LIST);
    }

    @Test
    public void test_successDecreaseSort() {
        AbstractSort<Integer> bubbleSort = new BubbleSort<>(UNSORTED_LIST, false);

        assertThat(bubbleSort.getSortedList()).isEqualTo(DECREASE_SORTED_LIST);
    }

    @Test
    public void test_successIncreaseSortForRandomGeneratedList() {
        final List<Integer> RANDOM_LIST = createRandomArray();

        AbstractSort<Integer> bubbleSort = new BubbleSort<>(RANDOM_LIST);
        RANDOM_LIST.sort(naturalOrder());

        assertThat(bubbleSort.getSortedList()).isEqualTo(RANDOM_LIST);
    }

    @Test
    public void test_successDecreaseSortForRandomGeneratedList() {
        final List<Integer> RANDOM_LIST = createRandomArray();

        AbstractSort<Integer> bubbleSort = new BubbleSort<>(RANDOM_LIST, false);
        RANDOM_LIST.sort(reverseOrder());

        assertThat(bubbleSort.getSortedList()).isEqualTo(RANDOM_LIST);
    }

}
