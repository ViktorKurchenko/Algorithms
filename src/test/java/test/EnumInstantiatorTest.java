package test;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class EnumInstantiatorTest {

    private static final EnumInstantiation INSTANTIATOR = new EnumInstantiation();

    @Test
    public void test() {
        assertEquals(StringEnum.STR_1, INSTANTIATOR.getEnum("test.StringEnum", "str-1"));
        assertEquals(StringEnum.STR_2, INSTANTIATOR.getEnum("test.StringEnum", "str-2"));
        assertEquals(StringEnum.STR_3, INSTANTIATOR.getEnum("test.StringEnum", "str-3"));

        assertEquals(IntEnum.INT_1, INSTANTIATOR.getEnum("test.IntEnum", 1));
        assertEquals(IntEnum.INT_2, INSTANTIATOR.getEnum("test.IntEnum", 2));
        assertEquals(IntEnum.INT_3, INSTANTIATOR.getEnum("test.IntEnum", 3));
    }


    @Test
    public void test2() {
        assertNull(INSTANTIATOR.getEnum("test.StringEnum", "fakeString"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test3() {
        INSTANTIATOR.getEnum("test.fakeClass", "fakeString");
    }

}
