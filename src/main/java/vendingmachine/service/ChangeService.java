package vendingmachine.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import vendingmachine.Coin;
import vendingmachine.domain.VendingMachine;
import vendingmachine.view.output.dto.ChangeDto;

public class ChangeService {
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