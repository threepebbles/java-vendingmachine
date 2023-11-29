package vendingmachine.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import vendingmachine.Coin;
import vendingmachine.error.ErrorMessage;

public class VendingMachine {
    private final Map<Coin, Integer> coinCount;
    private final Products products;
    private int remainingMoney;

    public VendingMachine(Products products) {
        this.coinCount = new HashMap<>() {{
            Arrays.stream(Coin.values()).forEach(coin -> put(coin, 0));
        }};
        this.products = products;
        this.remainingMoney = 0;
    }

    public void addCoins(Coin coin, int count) {
        coinCount.putIfAbsent(coin, 0);
        coinCount.put(coin, coinCount.get(coin) + count);
    }

    public void subtractCoins(Coin coin, int count) {
        if (coinCount.get(coin) == null || coinCount.get(coin) < count) {
            throw new IllegalArgumentException(ErrorMessage.getMessage("코인이 부족합니다."));
        }
        coinCount.put(coin, coinCount.get(coin) - count);
    }

    public void sellProducts(String productName, int count) {
        Product product = products.findProductByName(productName);
        product.subtractProduct(count);
        reduceMoney(product.getPrice() * count);
    }

    public void insertMoney(int money) {
        if (money < 0 || money % 10 != 0) {
            throw new IllegalArgumentException(ErrorMessage.getMessage("투입 금액이 올바르지 않습니다."));
        }
        remainingMoney += money;
    }

    public void reduceMoney(int money) {
        if (remainingMoney < money) {
            throw new IllegalArgumentException(ErrorMessage.getMessage("출금할 수 있는 금액보다 많은 금액입니다."));
        }
        remainingMoney -= money;
    }

    public boolean hasEnoughRemainingMoney() {
        return remainingMoney >= products.getLowestPrice();
    }

    public int getCountOfCoin(Coin coin) {
        return coinCount.get(coin);
    }

    public int getRemainingMoney() {
        return remainingMoney;
    }
}