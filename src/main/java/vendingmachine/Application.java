package vendingmachine;

import vendingmachine.controller.MainController;
import vendingmachine.view.input.InputView;
import vendingmachine.view.output.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        MainController mainController = new MainController(inputView, outputView);
        mainController.run();
    }
}