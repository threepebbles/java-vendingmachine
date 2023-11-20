package vendingmachine.util;

import java.util.List;
import java.util.Objects;

public class Validator {
    public static void checkNull(Object o, String errorMessage) {
        if (Objects.isNull(o)) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void checkBlank(String input, String errorMessage) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void checkIsInteger(String input, String errorMessage) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void checkIsPositiveInteger(int input, String errorMessage) {
        if (input <= 0) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void checkStringLength(String input, int length, String errorMessage) {
        if (input.length() != length) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static <T> void checkListLength(List<T> list, int targetLength, String errorMessage) {
        if (list.size() != targetLength) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void checkNumberInRange(int input, int min, int max, String errorMessage) {
        if (input > max || input < min) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}