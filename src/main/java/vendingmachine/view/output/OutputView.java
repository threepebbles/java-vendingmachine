package vendingmachine.view.output;

import vendingmachine.view.output.dto.InitialCoinsDto;

public class OutputView {
    private static final String LINT_SEPARATOR = System.lineSeparator();
    private static final String INITIAL_COINS_SCREEN = "자판기가 보유한 동전" + LINT_SEPARATOR
            + "%s";

    public void printChangeScreen(InitialCoinsDto initialCoinsDto) {
        System.out.printf(INITIAL_COINS_SCREEN, initialCoinsDto.toString());
    }
}