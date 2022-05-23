package kata.supermarket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {

    @DisplayName("basket provides its total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue(String description, String expectedTotal, Iterable<Item> items) {
        final Basket basket = new Basket();
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

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
        Item itemByWeight = new ItemByWeight(new WeighedProduct(new BigDecimal("1.00")), BigDecimal.ONE);
        assertThat(itemByUnit.getDiscountAmount())
                .isEqualTo(itemByWeight.getDiscountAmount())
                .isZero();
    }

    @Test
    @DisplayName("Buy one get one free is supported for ItemByUnit")
    void buyOneGetOneFree() {
        Product bogofProduct = new Product(new BigDecimal("0.70"), BuyOneGetOneFreeDiscount.getInstance());
        Item bogofItem = new ItemByUnit(bogofProduct, 5);
        Basket basket = new Basket();
        basket.add(bogofItem);
        assertThat(basket.total()).isEqualTo(new BigDecimal("2.10"));
    }


    static Stream<Arguments> basketProvidesTotalValue() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight()
        );
    }

    private static Arguments aSingleItemPricedByWeight() {
        return Arguments.of("a single weighed item", "1.25", Collections.singleton(twoFiftyGramsOfAmericanSweets()));
    }

    private static Arguments multipleItemsPricedByWeight() {
        return Arguments.of("multiple weighed items", "1.85",
                Arrays.asList(twoFiftyGramsOfAmericanSweets(), twoHundredGramsOfPickAndMix())
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "2.04",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()));
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.49", Collections.singleton(aPintOfMilk()));
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList());
    }

    private static Item aPintOfMilk() {
        return new Product(new BigDecimal("0.49")).oneOf();
    }

    private static Item aPackOfDigestives() {
        return new Product(new BigDecimal("1.55")).oneOf();
    }

    private static WeighedProduct aKiloOfAmericanSweets() {
        return new WeighedProduct(new BigDecimal("4.99"));
    }

    private static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
    }

    private static WeighedProduct aKiloOfPickAndMix() {
        return new WeighedProduct(new BigDecimal("2.99"));
    }

    private static Item twoHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".2"));
    }
}