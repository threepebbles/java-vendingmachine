package vendingmachine;

import vendingmachine.controller.MainController;
import vendingmachine.view.input.InputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        MainController mainController = new MainController(inputView);
        mainController.run();
    }
}