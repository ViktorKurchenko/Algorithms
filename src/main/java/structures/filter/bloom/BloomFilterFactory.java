package structures.filter.bloom;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.nCopies;
import static java.util.Objects.isNull;

public class BloomFilterFactory<T> {

    @SuppressWarnings("unchecked")
    public BloomFilterBuilder<T> createFilterBuilder(@NotNull BloomFilterType type) {
        return new BloomFilterBuilder<>(type);
    }

    public static final class BloomFilterBuilder<T> {

        public static final Integer DEFAULT_BIAS = 3;
        public static final Integer DEFAULT_CAPACITY = 1_000_000;

        private final BloomFilterType type;

        private int bias = DEFAULT_BIAS;
        private int capacity = DEFAULT_CAPACITY;
        private List<Integer> storage;


        BloomFilterBuilder(BloomFilterType type) {
            this.type = type;
        }

        public BloomFilterBuilder<T> withBias(int bias) {
            checkArgument(bias > 0, "Filter BIAS must be greater than ZERO");
            this.bias = bias;
            return this;
        }

        public BloomFilterBuilder<T> withCapacity(int capacity) {
            checkArgument(capacity > 0, "Filter capacity must be greater than ZERO");
            this.capacity = capacity;
            return this;
        }

        public BloomFilterBuilder<T> withStorage(@NotNull List<Integer> storage) {
            checkArgument(isNotEmpty(storage), "Filter storage must not be EMPTY");
            this.storage = newArrayList(storage);
            return this;
        }

        @NotNull
        public BloomFilter<T> build() {
            final BloomFilter<T> filter = type.getInstance(bias);
            filter.setFilterStorage(isEmpty(storage) ? nCopies(capacity, 0) : storage);
            return filter;
        }

        private boolean isNotEmpty(Collection<Integer> collection) {
            return !isEmpty(collection);
        }

        private boolean isEmpty(Collection<Integer> collection) {
            return isNull(collection) || collection.isEmpty();
        }

    }

}
