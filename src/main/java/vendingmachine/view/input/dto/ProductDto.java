package vendingmachine.view.input.dto;

import java.util.List;
import vendingmachine.ErrorMessage;
import vendingmachine.util.Parser;
import vendingmachine.util.Validator;

public class ProductDto {
    private static final String NOT_PROPER_PRODUCT = ErrorMessage.getMessage("올바르지 않은 입력입니다.");
    private final String name;
    private final int price;
    private final int count;

    public ProductDto(String name, int price, int count) {
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public static ProductDto createProductDto(String userInput) {
        validateProductFormat(userInput);
        userInput = userInput.substring(1, userInput.length() - 2);
        List<String> parsed = Parser.parseWithDelimiter(userInput, ",");

        String name = parsed.get(0);
        String price = parsed.get(1);
        String count = parsed.get(2);
        return new ProductDto(name, Integer.parseInt(price), Integer.parseInt(count));
    }

    private static void validateProductFormat(String product) {
        Validator.checkBlank(product, NOT_PROPER_PRODUCT);
        checkBracket(product);

        product = product.substring(1, product.length() - 1);
        List<String> parsed = Parser.parseWithDelimiter(product, ",");

        Validator.checkListLength(parsed, 3, NOT_PROPER_PRODUCT);
        Validator.checkBlank(parsed.get(0), NOT_PROPER_PRODUCT);
        Validator.checkBlank(parsed.get(1), NOT_PROPER_PRODUCT);
        Validator.checkBlank(parsed.get(2), NOT_PROPER_PRODUCT);
        parsed.forEach(ProductDto::checkBlank);
        Validator.checkIsInteger(parsed.get(1), NOT_PROPER_PRODUCT);
        Validator.checkIsInteger(parsed.get(2), NOT_PROPER_PRODUCT);
    }

    private static void checkBracket(String s) {
        if (s.indexOf('[') != 0 || s.indexOf(']') != s.length() - 1) {
            throw new IllegalArgumentException(NOT_PROPER_PRODUCT);
        }
    }

    private static void checkBlank(String s) {
        if (s.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.getMessage(NOT_PROPER_PRODUCT));
        }
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }
}