package structures.filter.bloom;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.Objects.nonNull;

public class BloomFilterFactory {

    public <T> BloomFilterBuilder<T> createFilterBuilder(@NotNull BloomFilterType type) {
        final BloomFilter<T> filter;
        try {
            filter = getConstructor(type).newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return new BloomFilterBuilder<>(filter);
    }

    private @NotNull Constructor<? extends BloomFilter> getConstructor(BloomFilterType filterType) throws NoSuchMethodException {
        Class<? extends BloomFilter> type = filterType.getType();
        Constructor<? extends BloomFilter> constructor = type.getDeclaredConstructor();
        constructor.setAccessible(true);
        return constructor;
    }

    public static final class BloomFilterBuilder<T> {

        private final BloomFilter<T> filter;

        private int bias = 3;
        private int capacity = 100;
        private List<Integer> storage;


        BloomFilterBuilder(BloomFilter<T> filter) {
            this.filter = filter;
        }

        public BloomFilterBuilder<T> withBias(int bias) {
            this.bias = bias;
            return this;
        }

        public BloomFilterBuilder<T> withCapacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public BloomFilterBuilder<T> withStorage(@NotNull List<Integer> storage) {
            Collections.copy(this.storage = newArrayList(), storage);
            return this;
        }

        public BloomFilter<T> build() {
            checkArgument(bias > 0, "Filter BIAS must be greater than ZERO");
            checkArgument(capacity > 0 || (nonNull(storage) && !storage.isEmpty()),
                    "Filter capacity must be greater than ZERO or filter storage must be provided");
            // TODO: fix me...
            return null;
        }
    }

}
