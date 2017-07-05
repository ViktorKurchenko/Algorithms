package algorithms.sort;

import java.util.List;
import java.util.Random;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.primitives.Ints.asList;
import static java.util.Collections.singletonList;

public abstract class SortTestCase {

    protected static final List<Integer> SINGLETON_LIST = singletonList(1);
    protected static final List<Integer> INCREASE_SORTED_LIST = asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    protected static final List<Integer> DECREASE_SORTED_LIST = asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0);
    protected static final List<Integer> UNSORTED_LIST = asList(1, 9, 2, 8, 3, 7, 4, 6, 5, 10, 0);

    protected static final int RANDOM_LIST_SIZE = 1_000;
    protected static final Random random = new Random();


    protected List<Integer> createRandomArray() {
        final List<Integer> list = newArrayList();
        for (int i = 0; i < RANDOM_LIST_SIZE; i++) {
            list.add(random.nextInt());
        }
        return list;
    }

}
