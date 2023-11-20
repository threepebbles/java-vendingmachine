package vendingmachine.view.input;


import camp.nextstep.edu.missionutils.Console;
import java.util.function.Supplier;
import vendingmachine.view.input.dto.InitialAmountDto;
import vendingmachine.view.input.dto.ProductsDto;
import vendingmachine.view.input.dto.PurchaseAmountDto;

public class InputView {
    public static final String LINE_SEPARATOR = System.lineSeparator();

    public InitialAmountDto requestInitialAmount() {
        return (InitialAmountDto) retryUntilSuccess(() -> {
            System.out.print("자판기가 보유하고 있는 금액을 입력해 주세요." + LINE_SEPARATOR);
            String initialAmount = Console.readLine();
            return InitialAmountDto.createInitialAmountDto(initialAmount);
        });
    }

    public ProductsDto requestProductsDto() {
        return (ProductsDto) retryUntilSuccess(() -> {
            System.out.print("상품명과 가격, 수량을 입력해 주세요." + LINE_SEPARATOR);
            String products = Console.readLine();
            return ProductsDto.createProductsDto(products);
        });
    }

    public PurchaseAmountDto requestPurchaseAmountDto() {
        return (PurchaseAmountDto) retryUntilSuccess(() -> {
            System.out.print("투입 금액을 입력해 주세요." + LINE_SEPARATOR);
            String purchaseAmount = Console.readLine();
            return PurchaseAmountDto.createPurchaseAmountDto(purchaseAmount);
        });
    }

//    public PurchaseProductDto requestPurchaseProductDto() {
//        return (PurchaseProductDto) retryUntilSuccess(() -> {
//            System.out.print("구매할 상품명을 입력해 주세요." + LINE_SEPARATOR);
//            String purchaseProduct = Console.readLine();
//            return PurchaseProductDto.createPurchaseProductDto(purchaseProduct);
//        });
//    }

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