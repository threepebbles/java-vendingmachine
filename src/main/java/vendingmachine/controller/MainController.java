package vendingmachine.controller;

import vendingmachine.view.input.InputView;
import vendingmachine.view.input.dto.InitialAmountDto;

public class MainController {
    private final InputView inputView;

    public MainController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        InitialAmountDto initialAmountDto = inputView.requestInitialAmount();
        System.out.println(initialAmountDto.getAmount());
    }

}
