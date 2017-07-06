package algorithms.sort;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.nonNull;

public class SortFactory {

    public static <T extends Comparable<T>> AbstractSort<T> sort(SortType sortType, List<T> list) {
        return sort(sortType, list, true);
    }

    public static <T extends Comparable<T>> AbstractSort<T> sort(SortType sortType, List<T> list, boolean ascendingOrder) {
        checkArgument(nonNull(list) && !list.isEmpty(), "List is empty!");
        try {
            AbstractSort<T> abstractSort = getConstructor(sortType).newInstance(list, ascendingOrder);
            return abstractSort.sort();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException(e);
        }
    }

    @NotNull
    private static Constructor<? extends AbstractSort> getConstructor(SortType sortType) throws NoSuchMethodException {
        Class<? extends AbstractSort> type = sortType.getType();
        Constructor<? extends AbstractSort> constructor = type.getDeclaredConstructor(List.class, Boolean.class);
        constructor.setAccessible(true);
        return constructor;
    }

}
