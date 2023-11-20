package vendingmachine.view.output.dto;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import vendingmachine.Coin;

public class InitialCoinsDto {
    private static final String LINT_SEPARATOR = System.lineSeparator();
    private final Map<Coin, Integer> coinCount;

    public InitialCoinsDto(Map<Coin, Integer> coinCount) {
        this.coinCount = coinCount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<Coin> coins = coinCount.keySet().stream().
                sorted(Comparator.comparing(Coin::getAmount).reversed())
                .toList();
        for (Coin coin : coins) {
            sb.append(String.format("%d원 - %d개", coin.getAmount(), coinCount.get(coin)))
                    .append(LINT_SEPARATOR);
        }
        return sb.toString();
    }
}