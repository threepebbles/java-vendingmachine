package vendingmachine;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public static Coin valueOf(int amount) {
        for (Coin coin : values()) {
            if (coin.getAmount() == amount) {
                return coin;
            }
        }
        return null;
    }

    public static int getMinimumUnit() {
        return Arrays.stream(values()).sorted(Comparator.comparing(Coin::getAmount))
                .toList()
                .get(0)
                .getAmount();
    }

    public static List<Coin> getCoinsSortedByAmountInDescendingOrder() {
        return Arrays.stream(Coin.values())
                .sorted(Comparator.comparing(Coin::getAmount).reversed())
                .toList();
    }

    public int getAmount() {
        return amount;
    }
}
