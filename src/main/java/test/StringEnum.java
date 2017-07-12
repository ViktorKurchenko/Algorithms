package test;

public enum StringEnum {

    STR_1("str-1"), STR_2("str-2"), STR_3("str-3");

    private final String string;

    StringEnum(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public static StringEnum of(String value) {
        for (StringEnum stringEnum : values()) {
            if (stringEnum.string.equals(value)) {
                return stringEnum;
            }
        }
        return null;
    }

}
