package kata.supermarket;

import java.math.BigDecimal;

interface Discount<T> {
    BigDecimal getDiscountAmount(T item);
}
