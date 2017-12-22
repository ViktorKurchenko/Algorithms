package structures.filter.bloom;

import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public enum BloomFilterType {

    SIMPLE(SimpleBloomFilter::new), COUNTING(CountingBloomFilter::new), EXPANDED(ExpandedBloomFilter::new);

    private final Function<Integer, ? extends BloomFilter> instance;

    BloomFilterType(Function<Integer, ? extends BloomFilter> instance) {
        this.instance = instance;
    }

    public <T> BloomFilter<T> getInstance(@NotNull Integer bias) {
        return instance.apply(bias);
    }

}
