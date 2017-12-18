package structures.filter.bloom;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface BloomFilter<T> {

    boolean test(@NotNull T object);

    void add(@NotNull T object);

    void remove(@NotNull T object);

    void clear();

    @NotNull List<Integer> getFilterStorage();

    void setFilterStorage(@NotNull List<Integer> storage);

}
