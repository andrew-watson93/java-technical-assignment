package kata.supermarket;

import java.math.BigDecimal;

public class BuyOneGetOneFreeDiscount implements Discount<ItemByUnit> {
    private BuyOneGetOneFreeDiscount() {
    }

    public static BuyOneGetOneFreeDiscount getInstance() {
        return instance;
    }

    private final static BuyOneGetOneFreeDiscount instance = new BuyOneGetOneFreeDiscount();

    public BigDecimal getDiscountAmount(ItemByUnit item) {
        if (item.getQuantity() == 0) return BigDecimal.ZERO;
        return BigDecimal.valueOf(item.getQuantity() / 2).multiply(item.unitPrice());
    }
}
