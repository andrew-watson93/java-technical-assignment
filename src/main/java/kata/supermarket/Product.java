package kata.supermarket;

import java.math.BigDecimal;

public class Product {

    private final BigDecimal pricePerUnit;

    private Discount<ItemByUnit> discount = null;

    public Product(final BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Product(final BigDecimal pricePerUnit, Discount<ItemByUnit> discount) {
        this.pricePerUnit = pricePerUnit;
        this.discount = discount;
    }

    Discount<ItemByUnit> getDiscount() {
        return discount;
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this, 1);
    }
}
