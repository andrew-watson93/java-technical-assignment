package kata.supermarket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NoneDiscountTest {

    @Test
    @DisplayName("None discount exists and applies no price change")
    void noneDiscount() {
        Item item = new ItemByUnit(new Product(new BigDecimal("0.70")), 10);
        assertThat(NoneDiscount.getInstance().getDiscountAmount(item)).isZero();
    }
}
