package kata.supermarket;

import java.math.BigDecimal;

public class ItemByUnit implements Item {

    private final Product product;

    private Integer quantity;

    ItemByUnit(final Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public BigDecimal price() {
        return product.pricePerUnit().multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public BigDecimal getDiscountAmount() {
        return BigDecimal.ZERO;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
