package vendingmachine.view.input.dto;

import java.util.List;
import vendingmachine.ErrorMessage;
import vendingmachine.util.Parser;

public class ProductDto {
    public static final String NOT_PROPER_FORMAT = "올바르지 않은 포맷입니다.";
    private final String name;
    private final int price;
    private final int count;

    public ProductDto(String name, int price, int count) {
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public static ProductDto createProductDto(String product) {
        validateProductFormat(product);
        product = product.substring(1, product.length() - 2);
        List<String> parsed = Parser.parseWithDelimiter(product, ",");
        String name = parsed.get(0);
        String price = parsed.get(1);
        String count = parsed.get(2);
        return new ProductDto(name, Integer.parseInt(price), Integer.parseInt(count));
    }

    private static void validateProductFormat(String product) {
        checkLength(product, 2);
        checkBracket(product);

        product = product.substring(1, product.length() - 1);
        System.out.println(product);

        List<String> parsed = Parser.parseWithDelimiter(product, ",");
        checkSize(parsed, 3);
        parsed.forEach(ProductDto::checkBlank);
        checkInteger(parsed.get(1));
        checkInteger(parsed.get(2));
    }

    private static void checkLength(String product, int length) {
        if (product.length() <= 2) {
            throw new IllegalArgumentException(ErrorMessage.getMessage(NOT_PROPER_FORMAT));
        }
    }

    private static void checkInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.getMessage(NOT_PROPER_FORMAT));
        }
    }

    private static void checkBracket(String s) {
        if (s.indexOf('[') != 0 || s.indexOf(']') != s.length() - 1) {
            throw new IllegalArgumentException(ErrorMessage.getMessage(NOT_PROPER_FORMAT));
        }
    }

    private static void checkBlank(String s) {
        if (s.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.getMessage(NOT_PROPER_FORMAT));
        }
    }

    private static void checkSize(List<String> parsed, int size) {
        if (parsed.size() != size) {
            throw new IllegalArgumentException(ErrorMessage.getMessage(NOT_PROPER_FORMAT));
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