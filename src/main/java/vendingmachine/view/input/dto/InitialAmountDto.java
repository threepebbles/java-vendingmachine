package vendingmachine.view.input.dto;

import vendingmachine.error.ErrorMessage;
import vendingmachine.util.Validator;

public class InitialAmountDto {
    private final int amount;

    public InitialAmountDto(int amount) {
        this.amount = amount;
    }

    public static InitialAmountDto createInitialAmountDto(String userInput) {
        Validator.checkIsInteger(userInput, ErrorMessage.getMessage("입력한 금액이 정수가 아닙니다."));
        int initialAmount = Integer.parseInt(userInput);
        Validator.checkIsPositiveInteger(initialAmount, ErrorMessage.getMessage("입력한 금액이 양수가 아닙니다."));
        return new InitialAmountDto(initialAmount);
    }

    public int getAmount() {
        return amount;
    }
}