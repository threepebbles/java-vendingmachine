package vendingmachine.util;

import java.util.Arrays;
import java.util.List;

public class Parser {
    public static List<String> parseWithDelimiter(String userInput, String delimiter) {
        return Arrays.stream(userInput.split(delimiter, -1)).toList();
    }
}
