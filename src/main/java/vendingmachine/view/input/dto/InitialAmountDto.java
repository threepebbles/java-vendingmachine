package vendingmachine.view.input.dto;

import vendingmachine.ErrorMessage;

public class InitialAmountDto {
    private final int amount;

    public InitialAmountDto(int amount) {
        this.amount = amount;
    }

    public static InitialAmountDto createInitialAmountDto(String userInput) {
        validateInteger(userInput);
        int initialAmount = Integer.parseInt(userInput);
        validatePositiveInteger(initialAmount);
        return new InitialAmountDto(initialAmount);
    }

    private static void validatePositiveInteger(int initialAmount) {
        if (initialAmount <= 0) {
            throw new IllegalArgumentException(ErrorMessage.getMessage("입력한 금액이 양수가 아닙니다."));
        }
    }

    private static void validateInteger(String userInput) {
        try {
            Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.getMessage("입력한 금액이 정수가 아닙니다."));
        }
    }

    public int getAmount() {
        return amount;
    }
}