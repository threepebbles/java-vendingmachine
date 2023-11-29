package vendingmachine.view.output.dto;

import java.util.List;
import java.util.Map;
import vendingmachine.Coin;

public class ChangeDto {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private final Map<Coin, Integer> coinCount;

    public ChangeDto(Map<Coin, Integer> coinCount) {
        this.coinCount = coinCount;
    }

    @Override
    public String toString() {
        if (coinCount.isEmpty()) {
            return "없음" + LINE_SEPARATOR;
        }
        StringBuilder sb = new StringBuilder();
        List<Coin> coins = Coin.getCoinsSortedByAmountInDescendingOrder();
        for (Coin coin : coins) {
            if (coinCount.get(coin) == null || coinCount.get(coin) == 0) {
                continue;
            }
            sb.append(String.format("%d원 - %d개", coin.getAmount(), coinCount.get(coin)))
                    .append(LINE_SEPARATOR);
        }
        return sb.toString();
    }
}