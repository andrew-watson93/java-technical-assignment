package kata.supermarket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ItemByUnitTest {

    @Test
    @DisplayName("Multiple units for ItemByUnit are supported")
    void multipleItemsByUnitTest() {
        ItemByUnit item = new ItemByUnit(new Product(new BigDecimal("0.70")), 10);
        assertThat(item.getQuantity()).isEqualTo(10);
        assertThat(item.price()).isEqualTo(new BigDecimal("7.00"));
    }

    @Test
    @DisplayName("By default ItemsByUnit do not have a discount")
    void noDiscountByDefault() {
        Item itemByUnit = new ItemByUnit(new Product(new BigDecimal("0.70")), 10);
        assertThat(itemByUnit.getDiscountAmount()).isZero();
    }
}