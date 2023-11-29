package vendingmachine.domain;

import java.util.Comparator;
import java.util.List;
import vendingmachine.error.ErrorMessage;
import vendingmachine.view.input.dto.ProductsDto;

public class Products {
    private final List<Product> products;

    public Products(List<Product> products) {
        this.products = products;
    }

    public static Products createProducts(ProductsDto productsDto) {
        return new Products(productsDto.getProducts().stream()
                .map(Product::createProduct)
                .toList());
    }

    public Product findProductByName(String productName) {
        for (Product product : products) {
            if (product.getName().equals(productName)) {
                return product;
            }
        }
        throw new IllegalArgumentException(ErrorMessage.getMessage("존재하지 않는 상품명입니다."));
    }

    public int getLowestPrice() {
        if (products.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.getMessage("상품이 존재하지 않습니다."));
        }

        return products.stream().sorted(Comparator.comparing(Product::getPrice))
                .toList()
                .get(0)
                .getPrice();
    }
}