package vendingmachine.domain;

import vendingmachine.ErrorMessage;
import vendingmachine.util.Validator;
import vendingmachine.view.input.dto.ProductDto;

public class Product {
    private String name;
    private int price;
    private int count;

    public Product(String name, int price, int count) {
        validateName(name);
        validatePrice(price);
        validateCount(count);
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public static Product createProduct(ProductDto productDto) {
        return new Product(productDto.getName(), productDto.getPrice(), productDto.getCount());
    }

    private void validateCount(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException(ErrorMessage.getMessage("상품 개수가 올바르지 않습니다."));
        }
    }

    private void validateName(String name) {
        Validator.checkBlank(name, ErrorMessage.getMessage("상품명이 공백입니다."));
    }

    private void validatePrice(int price) {
        if (price < 100 || price % 10 != 0) {
            throw new IllegalArgumentException(ErrorMessage.getMessage("상품가격이 올바르지 않습니다."));
        }
    }

    public void addProduct(int count) {
        this.count += count;
    }

    public void subtractProduct(int count) {
        if (this.count < count) {
            throw new IllegalArgumentException(ErrorMessage.getMessage("수량이 부족합니다."));
        }
        this.count -= count;
    }


    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }
}