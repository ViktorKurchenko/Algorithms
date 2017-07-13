package algorithms.sort;

import com.google.common.primitives.Ints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import static algorithms.sort.SortFactory.sort;
import static algorithms.sort.SortType.*;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.Arrays.asList;
import static java.util.Collections.reverseOrder;
import static java.util.Collections.singletonList;
import static java.util.Comparator.naturalOrder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.runners.Parameterized.Parameter;
import static org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class SortFactoryTest {

    private static final List<Integer> SINGLETON_LIST = singletonList(1);
    private static final List<Integer> INCREASE_SORTED_LIST = Ints.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    private static final List<Integer> DECREASE_SORTED_LIST = Ints.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
    private static final List<Integer> UNSORTED_LIST = Ints.asList(1, 9, 2, 8, 3, 7, 4, 6, 5, 10, 0);

    private static final int RANDOM_LIST_SIZE = 1_000;
    private static final Random random = new Random();

    @Parameters
    public static Collection<Object[]> data() {
        return asList(new Object[][]{{BUBBLE_SORT}, {SELECTION_SORT}, {INSERTION_SORT}, {MERGE_SORT}});
    }

    @Parameter
    public SortType sortType;


    @Test(expected = IllegalArgumentException.class)
    public void test_exceptionForNullArgument() {
        sort(sortType, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_exceptionForForEmptyList() {
        sort(sortType, new ArrayList<Integer>());
    }

    @Test
    public void test_sortListWithOneItem() {
        assertThat(sort(sortType, SINGLETON_LIST).getSortedList()).isEqualTo(SINGLETON_LIST);
    }

    @Test
    public void test_successAscendingSort() {
        assertThat(sort(sortType, UNSORTED_LIST).getSortedList()).isEqualTo(INCREASE_SORTED_LIST);
    }

    @Test
    public void test_successDescendingSort() {
        assertThat(sort(sortType, UNSORTED_LIST, false).getSortedList()).isEqualTo(DECREASE_SORTED_LIST);
    }

    @Test
    public void test_successAscendingSortForRandomGeneratedList() {
        final List<Integer> RANDOM_LIST = createRandomArray();
        RANDOM_LIST.sort(naturalOrder());

        assertThat(sort(sortType, RANDOM_LIST).getSortedList()).isEqualTo(RANDOM_LIST);
    }

    @Test
    public void test_successDescendingSortForRandomGeneratedList() {
        final List<Integer> RANDOM_LIST = createRandomArray();
        RANDOM_LIST.sort(reverseOrder());

        assertThat(sort(sortType, RANDOM_LIST, false).getSortedList()).isEqualTo(RANDOM_LIST);
    }

    private List<Integer> createRandomArray() {
        final List<Integer> list = newArrayList();
        for (int i = 0; i < RANDOM_LIST_SIZE; i++) {
            list.add(random.nextInt());
        }
        return list;
    }

}
