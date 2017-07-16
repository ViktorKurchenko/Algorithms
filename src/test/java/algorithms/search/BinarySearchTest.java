package algorithms.search;

import org.junit.Test;

import java.util.List;

import static com.google.common.primitives.Ints.asList;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class BinarySearchTest {

    private static final List<Integer> SORTED_LIST = asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    private static final BinarySearch<Integer> BINARY_SEARCH = new BinarySearch<>(SORTED_LIST);


    @Test
    public void test_successSearch() {
        SORTED_LIST.forEach(element ->
                assertThat(BINARY_SEARCH.search(element)).isEqualTo(element));
    }

    @Test
    public void test_failSearch() {
        assertThat(BINARY_SEARCH.search(-10)).isEqualTo(-1);
        assertThat(BINARY_SEARCH.search(-1)).isEqualTo(-1);
        assertThat(BINARY_SEARCH.search(10)).isEqualTo(-10);
        assertThat(BINARY_SEARCH.search(100)).isEqualTo(-10);
    }

    @Test
    public void test_failSearch2() {
        final BinarySearch<Integer> binarySearch = new BinarySearch<>(asList(1, 3, 7, 9));
        assertThat(binarySearch.search(0)).isEqualTo(-1);
        assertThat(binarySearch.search(1)).isEqualTo(0);
        assertThat(binarySearch.search(2)).isEqualTo(-1);
        assertThat(binarySearch.search(3)).isEqualTo(1);
        assertThat(binarySearch.search(4)).isEqualTo(-2);
        assertThat(binarySearch.search(5)).isEqualTo(-2);
        assertThat(binarySearch.search(6)).isEqualTo(-2);
        assertThat(binarySearch.search(7)).isEqualTo(2);
        assertThat(binarySearch.search(8)).isEqualTo(-3);
        assertThat(binarySearch.search(9)).isEqualTo(3);
        assertThat(binarySearch.search(10)).isEqualTo(-4);
    }

}
