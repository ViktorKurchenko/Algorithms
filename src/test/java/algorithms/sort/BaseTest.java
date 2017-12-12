package algorithms.sort;

import java.util.List;
import java.util.Random;

import static com.google.common.collect.Lists.newArrayList;

public class BaseTest {

    private static final int RANDOM_LIST_SIZE = 10_000;
    private static final Random random = new Random();


    protected List<Integer> createRandomArray() {
        final List<Integer> list = newArrayList();
        for (int i = 0; i < RANDOM_LIST_SIZE; i++) {
            list.add(random.nextInt());
        }
        return list;
    }


}
