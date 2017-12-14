package structures.filter.bloom;

public enum BloomFilterType {

    SIMPLE(SimpleBloomFilter.class), COUNTING(CountingBloomFilter.class), EXPANDED(ExpandedBloomFilter.class);

    private final Class<? extends BloomFilter> type;

    BloomFilterType(Class<? extends BloomFilter> type) {
        this.type = type;
    }

    public Class<? extends BloomFilter> getType() {
        return type;
    }

}
