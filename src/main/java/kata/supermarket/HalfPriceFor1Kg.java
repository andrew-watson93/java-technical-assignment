package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class HalfPriceFor1Kg implements Discount<ItemByWeight> {

    private static final BigDecimal TWO = new BigDecimal("2.0");

    private HalfPriceFor1Kg(){
    }

    public static HalfPriceFor1Kg getInstance() {
        return instance;
    }

    private static HalfPriceFor1Kg instance = new HalfPriceFor1Kg();

    @Override
    public BigDecimal getDiscountAmount(ItemByWeight item) {
        if (item.getWeightInKilos().compareTo(BigDecimal.ONE) < 0) return BigDecimal.ZERO;
        BigDecimal fullKgs = item.getWeightInKilos().setScale(0, RoundingMode.DOWN);
        return item.getProduct().pricePerKilo().divide(TWO, 2, RoundingMode.HALF_UP).multiply(fullKgs);
    }
}
