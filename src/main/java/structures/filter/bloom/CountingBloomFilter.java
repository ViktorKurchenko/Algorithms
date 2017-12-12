package structures.filter.bloom;

import org.jetbrains.annotations.NotNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class CountingBloomFilter<T> extends SimpleBloomFilter<T> {

    @Override
    public void add(@NotNull T object) {
        checkNotNull(object);
        for (int i : calculateIndexes(toByteArray(object))) {
            storage.set(i, storage.get(i) + 1);
        }
    }

    @Override
    public void remove(@NotNull T object) {
        checkNotNull(object);
        for (int i : calculateIndexes(toByteArray(object))) {
            if (storage.get(i) > 0) {
                storage.set(i, storage.get(i) - 1);
            }
        }
    }

}
