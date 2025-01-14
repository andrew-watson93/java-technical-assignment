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

    public BigDecimal unitPrice() {
        return product.pricePerUnit();
    }

    @Override
    public BigDecimal getDiscountAmount() {
        return product.getDiscount() == null ? BigDecimal.ZERO : product.getDiscount().getDiscountAmount(this);
    }

    public Integer getQuantity() {
        return quantity;
    }
}
