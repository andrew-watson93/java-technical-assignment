package kata.supermarket;

import java.math.BigDecimal;

public class ItemByWeight implements Item {

    private final WeighedProduct product;
    private final BigDecimal weightInKilos;

    ItemByWeight(final WeighedProduct product, final BigDecimal weightInKilos) {
        this.product = product;
        this.weightInKilos = weightInKilos;
    }

    public BigDecimal price() {
        return product.pricePerKilo().multiply(weightInKilos).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getWeightInKilos() {
        return weightInKilos;
    }

    public WeighedProduct getProduct() {
        return product;
    }

    @Override
    public BigDecimal getDiscountAmount() {
        return product.getDiscount() == null ? BigDecimal.ZERO : product.getDiscount().getDiscountAmount(this);
    }
}
