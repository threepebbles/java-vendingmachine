package vendingmachine.view.input.dto;

import vendingmachine.ErrorMessage;

public class PurchaseAmountDto {
    public static final String NOT_PROPER_PURCHASE_AMOUNT = "올바르지 않은 투입 금액입니다.";
    private final int amount;

    public PurchaseAmountDto(int amount) {
        this.amount = amount;
    }

    public static PurchaseAmountDto createPurchaseAmountDto(String purchaseAmount) {
        checkInteger(purchaseAmount);

        int amount = Integer.parseInt(purchaseAmount);
        checkPositiveInteger(amount);
        return new PurchaseAmountDto(amount);
    }

    private static void checkPositiveInteger(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(ErrorMessage.getMessage(NOT_PROPER_PURCHASE_AMOUNT));
        }
    }

    private static void checkInteger(String amount) {
        try {
            Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.getMessage(NOT_PROPER_PURCHASE_AMOUNT));
        }
    }

    public int getAmount() {
        return amount;
    }
}