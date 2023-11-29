package vendingmachine.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import vendingmachine.Coin;

public class VendingMachine {
    private final Map<Coin, Integer> coinCount;
    private final List<Product> products;
    private int remainingMoney;

    public VendingMachine(List<Product> products) {
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
            throw new IllegalArgumentException("코인이 부족합니다.");
        }
        coinCount.put(coin, coinCount.get(coin) - count);
    }

    public Product findProductByName(String productName) {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                return product;
            }
        }
        return null;
    }

    public void addProducts(String productName, int count) {
        Product product = findProductByName(productName);
        if (product == null) {
            throw new IllegalArgumentException("존재하지 않는 상품입니다.");
        }
        product.addProduct(count);
    }

    public void subtractProducts(String productName, int count) {
        Product product = findProductByName(productName);
        if (product == null) {
            throw new IllegalArgumentException("존재하지 않는 상품입니다.");
        }
        product.addProduct(count);
    }

    public void insertRemainingMoney(int money) {
        remainingMoney += money;
    }

    public void reduceRemainingMoney(int money) {
        if (remainingMoney < money) {
            throw new IllegalArgumentException("출금할 수 있는 금액보다 많은 금액입니다.");
        }
        remainingMoney -= money;
    }
}