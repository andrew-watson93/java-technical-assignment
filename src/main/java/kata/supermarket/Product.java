package kata.supermarket;

import java.math.BigDecimal;

public class Product {

    private final BigDecimal pricePerUnit;

    private Discount discount = NoneDiscount.getInstance();

    public Product(final BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Product(final BigDecimal pricePerUnit, Discount discount) {
        this.pricePerUnit = pricePerUnit;
        this.discount = discount;
    }

    Discount getDiscount() {
        return discount;
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this, 1);
    }
}
