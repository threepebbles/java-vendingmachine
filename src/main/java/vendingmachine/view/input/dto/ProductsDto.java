package vendingmachine.view.input.dto;

import java.util.List;
import vendingmachine.util.Parser;

public class NewProductsDto {
    private final List<NewProductDto> products;

    public NewProductsDto(List<NewProductDto> products) {
        this.products = products;
    }

    public static NewProductsDto createNewProductsDto(String userInput) {
        List<String> parsed = Parser.parseWithDelimiter(userInput, ";");
        return new NewProductsDto(parsed.stream()
                .map(NewProductDto::createNewProductDto)
                .toList());
    }

    private static String remove(String s, int fromIndex, int toIndex) {
        return s.substring(fromIndex, toIndex);
    }

    public List<NewProductDto> getProducts() {
        return products;
    }
}
