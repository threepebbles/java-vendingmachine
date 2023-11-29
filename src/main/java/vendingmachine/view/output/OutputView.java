package vendingmachine.view.output;

import vendingmachine.view.output.dto.ChangeDto;
import vendingmachine.view.output.dto.InitialCoinsDto;
import vendingmachine.view.output.dto.RemainingMoneyDto;

public class OutputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String INITIAL_COINS_SCREEN = "자판기가 보유한 동전" + LINE_SEPARATOR
            + "%s";
    private static final String REMAINING_MONEY_SCREEN = "투입 금액: %s" + LINE_SEPARATOR;
    private static final String CHANGE_SCREEN = "잔돈" + LINE_SEPARATOR
            + "%s";

    public void printInitialCoinsScreen(InitialCoinsDto initialCoinsDto) {
        System.out.printf(INITIAL_COINS_SCREEN, initialCoinsDto.toString());
    }

    public void printRemainingMoney(RemainingMoneyDto remainingMoneyDto) {
        System.out.printf(REMAINING_MONEY_SCREEN, remainingMoneyDto.toString());
    }

    public void printChangeScreen(ChangeDto changeDto) {
        System.out.printf(CHANGE_SCREEN, changeDto.toString());
    }
}