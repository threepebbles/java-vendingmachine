package vendingmachine.domain;

import java.util.Map;
import java.util.Set;
import vendingmachine.Coin;

public class InitialCoins {
    private final Map<Coin, Integer> coinCount;

    public InitialCoins(Map<Coin, Integer> coinCount) {
        this.coinCount = coinCount;
    }

    public Set<Coin> keySet() {
        return coinCount.keySet();
    }

    public Integer get(Coin coin) {
        return coinCount.get(coin);
    }
}
