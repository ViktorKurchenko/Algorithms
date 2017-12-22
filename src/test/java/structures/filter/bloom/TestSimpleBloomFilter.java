package structures.filter.bloom;

import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static java.util.Collections.emptyList;
import static java.util.Collections.nCopies;
import static org.assertj.core.api.Assertions.assertThat;
import static structures.filter.bloom.BloomFilterFactory.BloomFilterBuilder.DEFAULT_CAPACITY;
import static structures.filter.bloom.BloomFilterType.SIMPLE;

public class TestSimpleBloomFilter {

    @Test
    public void test_createFilterWithDefaults() {
        final BloomFilter<String> filter = new BloomFilterFactory<String>().createFilterBuilder(SIMPLE).build();

        assertThat(filter).isNotNull();
        assertThat(filter.getFilterStorage()).isNotEmpty().hasSize(DEFAULT_CAPACITY);
    }

    @Test
    public void test_createFilterWithPredefinedCapacity() {
        final int capacity = 2048;
        final BloomFilter<String> filter = new BloomFilterFactory<String>().createFilterBuilder(SIMPLE)
                .withCapacity(capacity)
                .build();

        assertThat(filter).isNotNull();
        assertThat(filter.getFilterStorage()).isNotEmpty().hasSize(capacity);
    }

    @Test
    public void test_createFilterWithPredefinedStorage() {
        final List<Integer> storage = nCopies(100, 0);
        final BloomFilter<String> filter = new BloomFilterFactory<String>().createFilterBuilder(SIMPLE)
                .withStorage(storage)
                .build();

        assertThat(filter).isNotNull();
        assertThat(filter.getFilterStorage()).isNotEmpty().hasSize(storage.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_createFilterWithInvalidBias() {
        new BloomFilterFactory<String>().createFilterBuilder(SIMPLE).withBias(-1).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_createFilterWithInvalidCapacity() {
        new BloomFilterFactory<String>().createFilterBuilder(SIMPLE).withCapacity(-1).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_createFilterWithInvalidStorage() {
        new BloomFilterFactory<String>().createFilterBuilder(SIMPLE).withStorage(emptyList()).build();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_removeIsNotSupported() {
        new BloomFilterFactory<String>().createFilterBuilder(SIMPLE).build().remove("");
    }

    @Test
    public void test_predefinedStorageIsImmutable() {
        final int storageSize = 100;
        final List<Integer> storage = newArrayList();
        for (int i = 0; i < storageSize; i++) {
            storage.add(0);
        }
        final BloomFilter<String> filter = new BloomFilterFactory<String>().createFilterBuilder(SIMPLE)
                .withStorage(storage)
                .build();
        storage.add(0);

        assertThat(filter).isNotNull();
        assertThat(filter.getFilterStorage()).isNotEmpty().hasSize(storageSize);
        assertThat(storage).hasSize(storageSize + 1);
    }

    @Test
    public void test_addTestClearTest() {
        final BloomFilter<String> filter = new BloomFilterFactory<String>().createFilterBuilder(SIMPLE).build();
        final Collection<String> testCollection = createTestCollection(100);

        testCollection.forEach(element -> {
            assertThat(filter.test(element)).isFalse();
            filter.add(element);
            assertThat(filter.test(element)).isTrue();
        });
        filter.clear();
        testCollection.forEach(element -> assertThat(filter.test(element)).isFalse());
    }

    @Test
    public void test_storageClone() {
        final BloomFilter<String> firstFilter = new BloomFilterFactory<String>().createFilterBuilder(SIMPLE).build();
        final Collection<String> testCollection = createTestCollection(100);
        testCollection.forEach(firstFilter::add);
        final BloomFilter<String> secondFilter = new BloomFilterFactory<String>().createFilterBuilder(SIMPLE)
                .withStorage(firstFilter.getFilterStorage())
                .build();
        firstFilter.clear();
        testCollection.forEach(element -> assertThat(secondFilter.test(element)).isTrue());
    }

    private Collection<String> createTestCollection(int size) {
        final Collection<String> collection = newHashSet();
        for (int i = 0; i < size; i++) {
            collection.add("Test-" + i);
        }
        return collection;
    }

}
