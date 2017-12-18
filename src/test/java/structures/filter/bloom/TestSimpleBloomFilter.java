package structures.filter.bloom;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static com.google.common.collect.Sets.newHashSet;
import static org.assertj.core.api.Assertions.assertThat;
import static structures.filter.bloom.BloomFilterType.SIMPLE;

public class TestSimpleBloomFilter {

    private static final int ELEMENTS_COUNT = 1000;
    private static final Collection<String> ELEMENTS = newHashSet();


    @BeforeClass
    public static void beforeClass() {
        for (int i = 0; i < ELEMENTS_COUNT; i++) {
            ELEMENTS.add("TEST_" + i);
        }
    }

    @Test
    public void test() {
        final BloomFilter<String> filter = new BloomFilterFactory<String>().createFilterBuilder(SIMPLE).build();
        ELEMENTS.forEach(element -> assertThat(filter.test(element)).isFalse());
        ELEMENTS.forEach(filter::add);
        ELEMENTS.forEach(element -> assertThat(filter.test(element)).isTrue());
        filter.clear();
        ELEMENTS.forEach(element -> assertThat(filter.test(element)).isFalse());
    }

    @Test
    public void test2() {
        final BloomFilter<String> filter = new BloomFilterFactory<String>().createFilterBuilder(SIMPLE).build();
        ELEMENTS.forEach(element -> assertThat(filter.test(element)).isFalse());
        ELEMENTS.forEach(filter::add);
        final List<Integer> storage = filter.getFilterStorage();
        ELEMENTS.forEach(element -> assertThat(filter.test(element)).isTrue());
        filter.clear();
        ELEMENTS.forEach(element -> assertThat(filter.test(element)).isFalse());

        final BloomFilter<String> filter2 = new BloomFilterFactory<String>().createFilterBuilder(SIMPLE).withStorage(storage).build();
        ELEMENTS.forEach(element -> assertThat(filter2.test(element)).isTrue());
    }

}
