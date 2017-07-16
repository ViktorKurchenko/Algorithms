package algorithms.search;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.Objects.nonNull;

public class BinarySearch <T extends Comparable<T>> {

    private final List<T> list;

    public BinarySearch(List<T> list) {
        checkArgument(nonNull(list) && !list.isEmpty(), "List is empty!");
        this.list = newArrayList(list);
    }

    public int search(T element) {
        checkNotNull(element);
        int probe = list.get(0).compareTo(element);
        if (probe > 0) {
            return -1;
        }
        probe = list.get(list.size() - 1).compareTo(element);
        if (probe < 0) {
            return -list.size();
        }
        return binarySearch(element, 0, list.size() - 1);
    }

    private int binarySearch(T element, int start, int end) {
        if (start > end) {
            return -start;
        }
        int probe = list.get(start).compareTo(element);
        if (probe == 0) {
            return start;
        }
        if (probe > 0) {
            return -start;
        }
        probe = list.get(end).compareTo(element);
        if (probe == 0) {
            return end;
        }
        if (probe < 0) {
            return -(end + 1);
        }
        final int MIDDLE = (start + end) / 2;
        probe = list.get(MIDDLE).compareTo(element);
        return probe == 0 ? MIDDLE :
               probe > 0 ?
                binarySearch(element, start + 1, MIDDLE) :
                binarySearch(element, MIDDLE, end - 1);
    }

}
