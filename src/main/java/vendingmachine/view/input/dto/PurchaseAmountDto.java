package vendingmachine.view.input.dto;

import vendingmachine.error.ErrorMessage;
import vendingmachine.util.Validator;

public class PurchaseAmountDto {
    private static final String NOT_PROPER_PURCHASE_AMOUNT = ErrorMessage.getMessage("올바르지 않은 투입 금액입니다.");
    private final int amount;

    public PurchaseAmountDto(int amount) {
        this.amount = amount;
    }

    public static PurchaseAmountDto createPurchaseAmountDto(String userInput) {
        Validator.checkIsInteger(userInput, NOT_PROPER_PURCHASE_AMOUNT);

        int amount = Integer.parseInt(userInput);
        Validator.checkIsPositiveInteger(amount, NOT_PROPER_PURCHASE_AMOUNT);
        return new PurchaseAmountDto(amount);
    }

    public int getAmount() {
        return amount;
    }
}