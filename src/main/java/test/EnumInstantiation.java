package test;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static java.util.Objects.nonNull;

public class EnumInstantiation {

    private static final String ENUM_CREATION_METHOD = "of";


    public Enum getEnum(String className, Object value) {
        try {
            return (Enum) getEnumMethod(className, getValueType(value)).invoke(null, value);
        } catch (IllegalAccessException | InvocationTargetException | ClassNotFoundException | NoSuchMethodException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Nullable
    private Class getValueType(Object value) {
        return nonNull(value) ? value.getClass() : null;
    }

    @NotNull
    private Method getEnumMethod(String className, Class valueType) throws NoSuchMethodException, ClassNotFoundException {
        Method method = Class.forName(className).getDeclaredMethod(ENUM_CREATION_METHOD, valueType);
        method.setAccessible(true);
        return method;
    }

}
