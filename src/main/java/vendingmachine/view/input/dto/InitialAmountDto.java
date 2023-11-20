package vendingmachine.view.input.dto;

import vendingmachine.ErrorMessage;
import vendingmachine.util.Validator;

public class InitialAmountDto {
    private static final String NOT_PROPER_INITIAL_AMOUNT = ErrorMessage.getMessage("입력한 금액이 정수가 아닙니다.");
    private final int amount;

    public InitialAmountDto(int amount) {
        this.amount = amount;
    }

    public static InitialAmountDto createInitialAmountDto(String userInput) {
        Validator.checkIsInteger(userInput, NOT_PROPER_INITIAL_AMOUNT);
        int initialAmount = Integer.parseInt(userInput);
        Validator.checkIsPositiveInteger(initialAmount, NOT_PROPER_INITIAL_AMOUNT);
        return new InitialAmountDto(initialAmount);
    }

    public int getAmount() {
        return amount;
    }
}