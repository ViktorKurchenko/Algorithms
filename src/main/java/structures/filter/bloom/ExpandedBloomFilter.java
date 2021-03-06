package structures.filter.bloom;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Collections.*;

public class ExpandedBloomFilter<T> implements BloomFilter<T> {

    private static final Integer DEFAULT_BIAS = 3;
    private static final Integer DEFAULT_CAPACITY = 1024;

    private static final MessageDigest MESSAGE_DIGEST;

    static {
        try {
            MESSAGE_DIGEST = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }

    final List<Integer> storage;

    private final Integer bias;
    private final Integer capacity;


    public ExpandedBloomFilter(@NotNull List<Integer> filter) {
        checkNotNull(filter);
        checkArgument(!filter.isEmpty());
        bias = DEFAULT_BIAS;
        capacity = filter.size();
        copy(storage = new ArrayList<>(), filter);
    }

    public ExpandedBloomFilter() {
        this(DEFAULT_BIAS, DEFAULT_CAPACITY);
    }

    public ExpandedBloomFilter(int capacity) {
        this(DEFAULT_BIAS, capacity);
    }

    public ExpandedBloomFilter(int bias, int capacity) {
        checkArgument(capacity > 0);
        checkArgument(bias > 0);
        this.bias = bias;
        this.capacity = capacity;
        storage = new ArrayList<>(nCopies(capacity, 0));
    }

    @Override
    public boolean test(@NotNull T object) {
        checkNotNull(object);
        boolean result = true;
        for (int i : calculateIndexes(toByteArray(object))) {
            if (storage.get(i) == 0) {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public void add(@NotNull T object) {
        checkNotNull(object);
        for (int i : calculateIndexes(toByteArray(object))) {
            storage.set(i, 1);
        }
    }

    @Override
    public boolean remove(@NotNull T object) {
        throw new UnsupportedOperationException("This filter doesn't support remove operation! Use Counting or Multidimensional filter instead!");
    }

    @Override
    public void clear() {
        fill(storage, 0);
    }

    @NotNull
    @Override
    public List<Integer> getFilterStorage() {
        return unmodifiableList(storage);
    }

    @Override
    public void setFilterStorage(@NotNull List<Integer> storage) {

    }

    private int[] calculateIndexes(byte[] bytes) {
        final int[] buckets = new int[bias];
        for (int i = 0; i < bias; i++) {
            buckets[i] = hashCode(bytes, (i + 1)) % capacity;
        }
        return buckets;
    }

    private byte[] toByteArray(T object) {
        try (final ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            final ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(object);
            out.flush();
            return bos.toByteArray();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private Integer hashCode(byte[] bytes, int times) {
        for (int i = 0; i < times; i++) {
            bytes = MESSAGE_DIGEST.digest(bytes);
        }
        int hashCode = Arrays.hashCode(bytes);
        return hashCode < 0 ? (hashCode * -1) : hashCode;
    }

}
