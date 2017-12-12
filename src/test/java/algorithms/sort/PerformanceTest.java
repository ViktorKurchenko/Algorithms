package algorithms.sort;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PerformanceTest extends BaseTest {

    private static final SortFactory SORT_FACTORY = new SortFactory();


    @Test
    public void test_sortPerformance() {
        final List<Integer> RANDOM_LIST = createRandomArray();
        final long BUBBLE_SORT_TIME = benchmarkSort(SortType.BUBBLE_SORT, RANDOM_LIST);
        final long SELECTION_SORT_TIME = benchmarkSort(SortType.SELECTION_SORT, RANDOM_LIST);
        final long INSERTION_SORT_TIME = benchmarkSort(SortType.INSERTION_SORT, RANDOM_LIST);
        final long MERGE_SORT_TIME = benchmarkSort(SortType.MERGE_SORT, RANDOM_LIST);

        assertThat(MERGE_SORT_TIME).isLessThan(INSERTION_SORT_TIME);
        assertThat(INSERTION_SORT_TIME).isLessThan(SELECTION_SORT_TIME);
        assertThat(SELECTION_SORT_TIME).isLessThan(BUBBLE_SORT_TIME);
    }

    private long benchmarkSort(SortType sortType, List<Integer> list) {
        final long START = System.nanoTime();
        SORT_FACTORY.sort(sortType, list);
        final long END = System.nanoTime();
        return END - START;
    }

}
