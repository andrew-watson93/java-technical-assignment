package kata.supermarket;

import java.math.BigDecimal;

public class WeighedProduct {

    private final BigDecimal pricePerKilo;

    private Discount<ItemByWeight> discount = null;

    public WeighedProduct(final BigDecimal pricePerKilo) {
        this.pricePerKilo = pricePerKilo;
    }

    public WeighedProduct(final BigDecimal pricePerKilo, Discount<ItemByWeight> discount) {
        this.pricePerKilo = pricePerKilo;
        this.discount = discount;
    }

    BigDecimal pricePerKilo() {
        return pricePerKilo;
    }

    Discount<ItemByWeight> getDiscount() {
        return discount;
    }

    public Item weighing(final BigDecimal kilos) {
        return new ItemByWeight(this, kilos);
    }
}
