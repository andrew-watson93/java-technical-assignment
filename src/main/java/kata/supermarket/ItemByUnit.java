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
        Discount<ItemByUnit> discount = product.getDiscount();
        if (discount == null) return BigDecimal.ZERO;
        return discount.getDiscountAmount(this);
    }

    public Integer getQuantity() {
        return quantity;
    }
}
