package algorithms.sort;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Objects.nonNull;

public class SortFactory {

    public static <T extends Comparable<T>, S extends AbstractSort<T>> S sort(SortType sortType, List<T> list) {
        return sort(sortType, list, true);
    }

    public static <T extends Comparable<T>, S extends AbstractSort<T>> S sort(SortType sortType, List<T> list, boolean increasingOrder) {
        checkArgument(nonNull(list) && !list.isEmpty(), "List is empty!");
        try {
            Class<S> type = sortType.getType();
            S abstractSort = type.getDeclaredConstructor(List.class, Boolean.class).newInstance(list, increasingOrder);
            abstractSort.sort();
            return abstractSort;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalStateException(e);
        }
    }

}
