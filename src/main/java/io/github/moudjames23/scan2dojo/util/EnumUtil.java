package io.github.moudjames23.scan2dojo.util;

import io.github.moudjames23.scan2dojo.enums.EnumWithValue;

public class EnumUtil {

    private EnumUtil(){}

    public static <E extends Enum<E> & EnumWithValue> boolean isStringInEnum(String value, Class<E> enumClass) {
        if (value == null || enumClass == null) {
            return false;
        }

        for (E enumConstant : enumClass.getEnumConstants()) {
            if (enumConstant.getValue().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
