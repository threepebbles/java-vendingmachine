package vendingmachine.view.input.dto;

import vendingmachine.error.ErrorMessage;
import vendingmachine.util.Validator;

public class PurchaseProductDto {
    private final static String NOT_PROPER_PRODUCT_NAME = ErrorMessage.getMessage("올바르지 않은 상품명입니다.");
    private final String name;

    public PurchaseProductDto(String name) {
        this.name = name;
    }

    public static PurchaseProductDto createPurchaseProductDto(String userInput) {
        Validator.checkBlank(userInput, NOT_PROPER_PRODUCT_NAME);
        return new PurchaseProductDto(userInput);
    }

    public String getName() {
        return name;
    }
}