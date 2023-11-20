package vendingmachine.view.input;


import camp.nextstep.edu.missionutils.Console;
import java.util.function.Supplier;
import vendingmachine.view.input.dto.InitialAmountDto;

public class InputView {
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String ENTER_INITIAL_AMOUNT = "자판기가 보유하고 있는 금액을 입력해 주세요." + LINE_SEPARATOR;

    public InitialAmountDto requestInitialAmount() {
        return (InitialAmountDto) retryUntilSuccess(() -> {
            System.out.print(ENTER_INITIAL_AMOUNT);
            String initialAmount = Console.readLine();
            return InitialAmountDto.createInitialAmountDto(initialAmount);
        });
    }

    private <T> Object retryUntilSuccess(Supplier<T> function) {
        while (true) {
            try {
                return function.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}