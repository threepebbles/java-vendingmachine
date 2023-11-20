package vendingmachine.controller;

import vendingmachine.view.input.InputView;
import vendingmachine.view.input.dto.InitialAmountDto;
import vendingmachine.view.input.dto.ProductsDto;
import vendingmachine.view.input.dto.PurchaseAmountDto;

public class MainController {
    private final InputView inputView;

    public MainController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        InitialAmountDto initialAmountDto = inputView.requestInitialAmount();
        System.out.println(initialAmountDto.getAmount());

        ProductsDto productsDto = inputView.requestProductsDto();

        PurchaseAmountDto purchaseAmountDto = inputView.requestPurchaseAmountDto();
        System.out.println(purchaseAmountDto.getAmount());
        
    }
}