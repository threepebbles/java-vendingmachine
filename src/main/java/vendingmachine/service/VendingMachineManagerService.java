package vendingmachine.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import vendingmachine.Coin;
import vendingmachine.domain.CoinGenerator;
import vendingmachine.domain.InitialCoins;
import vendingmachine.domain.Products;
import vendingmachine.domain.VendingMachine;
import vendingmachine.error.ErrorMessage;
import vendingmachine.view.output.dto.InitialCoinsDto;

public class VendingMachineManagerService {
    public VendingMachine createVendingMachine(InitialCoins initialCoins, Products products) {
        VendingMachine vendingMachine = new VendingMachine(products);
        for (Coin coin : initialCoins.keySet()) {
            vendingMachine.addCoins(coin, initialCoins.get(coin));
        }
        return vendingMachine;
    }

    public InitialCoinsDto createInitialCoins(CoinGenerator coinGenerator, int amount) {
        checkDividedByMinimumCoinUnit(amount);
        Map<Coin, Integer> coinCount = getInitialCoinCount();
        while (amount > 0) {
            Coin coin = coinGenerator.generateCoin();
            if (amount >= coin.getAmount()) {
                int count = amount / coin.getAmount();
                coinCount.put(coin, coinCount.get(coin) + count);
                amount -= coin.getAmount() * count;
            }
        }
        return new InitialCoinsDto(coinCount);
    }

    private void checkDividedByMinimumCoinUnit(int amount) {
        if (amount % Coin.getMinimumUnit() != 0) {
            throw new IllegalArgumentException(ErrorMessage.getMessage("유효하지 않은 금액입니다."));
        }
    }

    private Map<Coin, Integer> getInitialCoinCount() {
        return new HashMap<>() {{
            Arrays.stream(Coin.values()).forEach(coin -> put(coin, 0));
        }};
    }
}