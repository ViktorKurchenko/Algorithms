package structures.filter.bloom;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface BloomFilter<T> {

    boolean test(@NotNull T object);

    void add(@NotNull T object);

    void remove(@NotNull T object);

    void clear();

    @NotNull Collection<Integer> getFilterStorage();

}
