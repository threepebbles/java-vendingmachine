package vendingmachine.controller;

import java.util.function.Supplier;
import vendingmachine.domain.CoinRandomGenerator;
import vendingmachine.domain.InitialCoins;
import vendingmachine.domain.Products;
import vendingmachine.domain.VendingMachine;
import vendingmachine.service.ChangeService;
import vendingmachine.service.SellingProductService;
import vendingmachine.service.VendingMachineManagerService;
import vendingmachine.view.input.InputView;
import vendingmachine.view.input.dto.InitialAmountDto;
import vendingmachine.view.input.dto.ProductsDto;
import vendingmachine.view.input.dto.PurchaseAmountDto;
import vendingmachine.view.input.dto.PurchaseProductDto;
import vendingmachine.view.output.OutputView;
import vendingmachine.view.output.dto.ChangeDto;
import vendingmachine.view.output.dto.InitialCoinsDto;
import vendingmachine.view.output.dto.RemainingMoneyDto;

public class MainController {
    private final InputView inputView;
    private final OutputView outputView;
    private final VendingMachineManagerService vendingMachineManagerService = new VendingMachineManagerService();
    private final SellingProductService sellingProductService = new SellingProductService();
    private final ChangeService changeService = new ChangeService();

    public MainController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        VendingMachine vendingMachine = createVendingMachine();

        startSelling(vendingMachine);

        returnChange(vendingMachine);
    }

    private VendingMachine createVendingMachine() {
        return (VendingMachine) retryUntilSuccessWithReturn(() -> {
            InitialCoins initialCoins = requestInitialCoins();
            Products products = requestProducts();
            return vendingMachineManagerService.createVendingMachine(initialCoins, products);
        });
    }

    private InitialCoins requestInitialCoins() {
        return (InitialCoins) retryUntilSuccessWithReturn(() -> {
            InitialAmountDto initialAmountDto = inputView.requestInitialAmountDto();
            int initialAmount = initialAmountDto.getAmount();
            InitialCoinsDto initialCoinsDto = vendingMachineManagerService.createInitialCoins(new CoinRandomGenerator(),
                    initialAmount);
            outputView.printInitialCoinsScreen(initialCoinsDto);
            return new InitialCoins(initialCoinsDto.getCoinCount());
        });
    }
    
    private Products requestProducts() {
        return (Products) retryUntilSuccessWithReturn(() -> {
            ProductsDto productsDto = inputView.requestProductsDto();
            return Products.createProducts(productsDto);
        });
    }

    private void startSelling(VendingMachine vendingMachine) {
        insertMoney(vendingMachine);
        retryUntilSuccess(() -> {
            while (vendingMachine.hasEnoughRemainingMoney()) {
                outputView.printRemainingMoney(requestRemainingMoneyDto(vendingMachine));
                String productName = requestProductName();
                sellingProductService.purchaseProduct(vendingMachine, productName);
            }
        });
    }

    private void insertMoney(VendingMachine vendingMachine) {
        retryUntilSuccess(() -> {
            int purchaseAmount = requestPurchaseAmount();
            sellingProductService.insertMoney(vendingMachine, purchaseAmount);
        });
    }

    private Integer requestPurchaseAmount() {
        return (Integer) retryUntilSuccessWithReturn(() -> {
            PurchaseAmountDto purchaseAmountDto = inputView.requestPurchaseAmountDto();
            return purchaseAmountDto.getAmount();
        });
    }

    private String requestProductName() {
        return (String) retryUntilSuccessWithReturn(() -> {
            PurchaseProductDto purchaseProductDto = inputView.requestPurchaseProductDto();
            return purchaseProductDto.getName();
        });
    }

    private void returnChange(VendingMachine vendingMachine) {
        ChangeDto changeDto = changeService.requestChangeDto(vendingMachine);
        outputView.printRemainingMoney(requestRemainingMoneyDto(vendingMachine));
        outputView.printChangeScreen(changeDto);
    }

    public RemainingMoneyDto requestRemainingMoneyDto(VendingMachine vendingMachine) {
        return new RemainingMoneyDto(vendingMachine.getRemainingMoney());
    }

    private <T> Object retryUntilSuccessWithReturn(Supplier<T> function) {
        while (true) {
            try {
                return function.get();
            } catch (IllegalArgumentException e) {
                inputView.printlnMessage(e.getMessage());
            }
        }
    }

    private void retryUntilSuccess(Runnable toRun) {
        while (true) {
            try {
                toRun.run();
                return;
            } catch (IllegalArgumentException e) {
                inputView.printlnMessage(e.getMessage());
            }
        }
    }
}