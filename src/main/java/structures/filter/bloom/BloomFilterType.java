package structures.filter.bloom;

import java.util.function.Supplier;

public enum BloomFilterType {

    SIMPLE(SimpleBloomFilter::new), COUNTING(CountingBloomFilter::new), EXPANDED(ExpandedBloomFilter::new);

    private final Supplier<? extends BloomFilter> instance;

    BloomFilterType(Supplier<? extends BloomFilter> instance) {
        this.instance = instance;
    }

    public BloomFilter getInstance() {
        return instance.get();
    }

}
