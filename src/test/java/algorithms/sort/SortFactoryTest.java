package algorithms.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static algorithms.sort.SortFactory.sort;
import static java.util.Arrays.asList;
import static java.util.Collections.reverseOrder;
import static java.util.Comparator.naturalOrder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class SortFactoryTest extends SortTestCase {

    @Test
    public void test_exceptionForNullArgument() {
        asList(SortType.values()).forEach(sortType -> {
            try {
                sort(sortType, null);
                fail("IllegalArgumentException should be thrown!");
            } catch (IllegalArgumentException ignored) {}
        });
    }

    @Test
    public void test_exceptionForForEmptyList() {
        asList(SortType.values()).forEach(sortType -> {
            try {
                sort(sortType, new ArrayList<Integer>());
                fail("IllegalArgumentException should be thrown!");
            } catch (IllegalArgumentException ignored) {}
        });
    }

    @Test
    public void test_sortListWithOneItem() {
        asList(SortType.values()).forEach(sortType ->
                assertThat(sort(sortType, SINGLETON_LIST).getSortedList()).isEqualTo(SINGLETON_LIST));
    }

    @Test
    public void test_successIncreaseSort() {
        asList(SortType.values()).forEach(sortType ->
                assertThat(sort(sortType, UNSORTED_LIST).getSortedList()).isEqualTo(INCREASE_SORTED_LIST));
    }

    @Test
    public void test_successDecreaseSort() {
        asList(SortType.values()).forEach(sortType ->
                assertThat(sort(sortType, UNSORTED_LIST, false).getSortedList()).isEqualTo(DECREASE_SORTED_LIST));
    }

    @Test
    public void test_successIncreaseSortForRandomGeneratedList() {
        final List<Integer> RANDOM_LIST = createRandomArray();
        RANDOM_LIST.sort(naturalOrder());

        asList(SortType.values()).forEach(sortType ->
                assertThat(sort(sortType, RANDOM_LIST).getSortedList()).isEqualTo(RANDOM_LIST));
    }

    @Test
    public void test_successDecreaseSortForRandomGeneratedList() {
        final List<Integer> RANDOM_LIST = createRandomArray();
        RANDOM_LIST.sort(reverseOrder());

        asList(SortType.values()).forEach(sortType ->
                assertThat(sort(sortType, RANDOM_LIST, false).getSortedList()).isEqualTo(RANDOM_LIST));
    }

}
