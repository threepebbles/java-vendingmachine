package vendingmachine.view.input.dto;

import java.util.List;
import vendingmachine.util.Parser;

public class ProductsDto {
    private final List<ProductDto> products;

    public ProductsDto(List<ProductDto> products) {
        this.products = products;
    }

    public static ProductsDto createProductsDto(String userInput) {
        List<String> parsed = Parser.parseWithDelimiter(userInput, ";");
        return new ProductsDto(parsed.stream()
                .map(ProductDto::createProductDto)
                .toList());
    }

    private static String remove(String s, int fromIndex, int toIndex) {
        return s.substring(fromIndex, toIndex);
    }

    public List<ProductDto> getProducts() {
        return products;
    }
}
