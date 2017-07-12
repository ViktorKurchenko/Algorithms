package test;

public enum IntEnum {

    INT_1(1), INT_2(2), INT_3(3);

    private final Integer id;

    IntEnum(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static IntEnum of(Integer value) {
        for (IntEnum intEnum : values()) {
            if (intEnum.id.equals(value)) {
                return intEnum;
            }
        }
        return null;
    }

}
