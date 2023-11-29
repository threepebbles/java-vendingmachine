package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.Arrays;
import java.util.List;
import vendingmachine.Coin;

public class CoinRandomGenerator implements CoinGenerator {
    public Coin generateCoin() {
        List<Integer> coins = Arrays.stream(Coin.values())
                .mapToInt(Coin::getAmount).boxed()
                .toList();
        int coinAmount = Randoms.pickNumberInList(coins);
        return Coin.valueOf(coinAmount);
    }
}