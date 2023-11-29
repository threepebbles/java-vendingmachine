package vendingmachine.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import vendingmachine.Coin;
import vendingmachine.domain.VendingMachine;
import vendingmachine.view.output.dto.ChangeDto;
import vendingmachine.view.output.dto.RemainingMoneyDto;

public class SalesService {
    public void insertMoney(VendingMachine vendingMachine, int amount) {
        vendingMachine.insertMoney(amount);
    }

    public void purchaseProduct(VendingMachine vendingMachine, String productName) {
        vendingMachine.sellProducts(productName, 1);
    }

    public RemainingMoneyDto requestRemainingMoneyDto(VendingMachine vendingMachine) {
        return new RemainingMoneyDto(vendingMachine.getRemainingMoney());
    }

    public ChangeDto requestChangeDto(VendingMachine vendingMachine) {
        Map<Coin, Integer> change = new HashMap<>();
        List<Coin> coins = Coin.getCoinsSortedByAmountInDescendingOrder();
        for (Coin coin : coins) {
            if (vendingMachine.getCountOfCoin(coin) == 0 || vendingMachine.getRemainingMoney() < coin.getAmount()) {
                continue;
            }
            int count = Math.min(vendingMachine.getRemainingMoney() / coin.getAmount(),
                    vendingMachine.getCountOfCoin(coin));
            vendingMachine.subtractCoins(coin, count);
            change.put(coin, count);
        }
        return new ChangeDto(change);
    }
}
