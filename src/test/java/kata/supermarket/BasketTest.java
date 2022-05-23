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
    @DisplayName("Buy one get one free is supported for ItemByUnit")
    void buyOneGetOneFree() {
        Product bogofProduct = new Product(new BigDecimal("0.70"), BuyOneGetOneFreeDiscount.getInstance());
        Item bogofItem = new ItemByUnit(bogofProduct, 5);
        Basket basket = new Basket();
        basket.add(bogofItem);
        assertThat(basket.total()).isEqualTo(new BigDecimal("2.10"));
    }

    @Test
    @DisplayName("Buy one get one free not applied when insufficient items")
    void buyOneGetOneFreeNotAppliedWhenInSufficientItems() {
        Product bogofProduct = new Product(new BigDecimal("0.70"), BuyOneGetOneFreeDiscount.getInstance());
        Item bogofItem = new ItemByUnit(bogofProduct, 1);
        Basket basket = new Basket();
        basket.add(bogofItem);
        assertThat(basket.total()).isEqualTo(new BigDecimal("0.70"));
    }

    @Test
    @DisplayName("Half price for weight discount is supported for ItemByWeight")
    void halfPriceByVolume() {
        Item itemWithWeightDiscount = new WeighedProduct(new BigDecimal("5.00"), HalfPriceFor1Kg.getInstance()).weighing(new BigDecimal("2.5"));
        Basket basket = new Basket();
        basket.add(itemWithWeightDiscount);
        assertThat(basket.total()).isEqualTo(new BigDecimal("7.50"));
    }

    @Test
    @DisplayName("Half price for weight discount not applied for insufficient weight")
    void halfPriceByVolumeNotAppliedWhenInsufficientWeight() {
        Item itemWithWeightDiscount = new WeighedProduct(new BigDecimal("5.00"), HalfPriceFor1Kg.getInstance()).weighing(new BigDecimal("0.5"));
        Basket basket = new Basket();
        basket.add(itemWithWeightDiscount);
        assertThat(basket.total()).isEqualTo(new BigDecimal("2.50"));
    }

    @Test
    @DisplayName("Test with all types of item in basket")
    void multipleItemsInBasketTest() {
        Item singleItemByUnit = new Product(new BigDecimal("1.5")).oneOf();
        Item multipleItemByUnit = new ItemByUnit(new Product(new BigDecimal("1")), 5);
        Item multipleItemByUnitWithDiscount = new ItemByUnit(new Product(new BigDecimal("2"), BuyOneGetOneFreeDiscount.getInstance()), 3);
        Item itemByWeight = new ItemByWeight(new WeighedProduct(new BigDecimal("2")), new BigDecimal("0.5"));
        Item itemByWeightWithDiscount = new ItemByWeight(new WeighedProduct(new BigDecimal("3"), HalfPriceFor1Kg.getInstance()), new BigDecimal("1.5"));
        Basket basket = new Basket();
        basket.add(singleItemByUnit);
        basket.add(multipleItemByUnit);
        basket.add(multipleItemByUnitWithDiscount);
        basket.add(itemByWeight);
        basket.add(itemByWeightWithDiscount);

        assertThat(basket.total()).isEqualTo(new BigDecimal("14.50"));
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