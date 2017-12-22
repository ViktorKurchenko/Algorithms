package structures.filter.bloom;

import org.junit.Test;

import java.util.Collection;

import static com.google.common.collect.Sets.newHashSet;
import static org.assertj.core.api.Assertions.assertThat;
import static structures.filter.bloom.BloomFilterType.COUNTING;

public class TestCountingBloomFilter {

    @Test
    public void test_addTestRemoveTest() {
        final BloomFilter<String> filter = new BloomFilterFactory<String>().createFilterBuilder(COUNTING).build();
        final Collection<String> testCollection = createTestCollection(100);

        testCollection.forEach(element -> {
            assertThat(filter.test(element)).isFalse();
            filter.add(element);
            assertThat(filter.test(element)).isTrue();
            assertThat(filter.remove(element)).isTrue();
            assertThat(filter.test(element)).isFalse();
        });
    }

    @Test
    public void test_notExistRemove() {
        final BloomFilter<String> filter = new BloomFilterFactory<String>().createFilterBuilder(COUNTING).build();
        assertThat(filter.remove("1")).isFalse();
    }

    private Collection<String> createTestCollection(int size) {
        final Collection<String> collection = newHashSet();
        for (int i = 0; i < size; i++) {
            collection.add("Test-" + i);
        }
        return collection;
    }

}
