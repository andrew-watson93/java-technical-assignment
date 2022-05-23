package kata.supermarket;

import java.math.BigDecimal;

public class NoneDiscount {

    private NoneDiscount() {
    }

    public static NoneDiscount getInstance() {
        return instance;
    }

    private static NoneDiscount instance = new NoneDiscount();

    public BigDecimal getDiscountAmount(Item item) {
        return BigDecimal.ZERO;
    }
}
