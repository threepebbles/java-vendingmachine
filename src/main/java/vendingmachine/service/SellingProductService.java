package vendingmachine.service;

import vendingmachine.domain.VendingMachine;

public class SellingProductService {
    public void insertMoney(VendingMachine vendingMachine, int amount) {
        vendingMachine.insertMoney(amount);
    }

    public void purchaseProduct(VendingMachine vendingMachine, String productName) {
        vendingMachine.sellProducts(productName, 1);
    }
}
