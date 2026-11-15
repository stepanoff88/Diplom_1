package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BurgerTest {

    private static final float BUN_PRICE = 100.0f;
    private static final float SAUCE_PRICE = 50.0f;
    private static final float FILLING_PRICE = 200.0f;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient sauce;

    @Mock
    private Ingredient filling;

    private Burger burger;

    @BeforeEach
    void setUp() {
        burger = new Burger();
    }

    @Test
    @DisplayName("setBuns сохраняет булочку в бургере")
    void setBunsShouldStoreBun() {
        burger.setBuns(bun);

        assertSame(bun, burger.bun);
    }

    @Test
    @DisplayName("addIngredient добавляет ингредиент в бургер")
    void addIngredientShouldAddIngredient() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        assertEquals(List.of(sauce, filling), burger.ingredients);
    }

    @Test
    @DisplayName("getPrice складывает цену двух булочек и всех ингредиентов")
    void getPriceShouldSumBunAndIngredientPrices() {
        when(bun.getPrice()).thenReturn(BUN_PRICE);
        when(sauce.getPrice()).thenReturn(SAUCE_PRICE);
        when(filling.getPrice()).thenReturn(FILLING_PRICE);
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        float actualPrice = burger.getPrice();

        assertEquals(2 * BUN_PRICE + SAUCE_PRICE + FILLING_PRICE, actualPrice);
        verify(bun).getPrice();
        verify(sauce).getPrice();
        verify(filling).getPrice();
    }

    @Test
    @DisplayName("getPrice возвращает удвоенную цену булочки, если ингредиентов нет")
    void getPriceShouldReturnOnlyBunPriceWhenNoIngredients() {
        when(bun.getPrice()).thenReturn(BUN_PRICE);
        burger.setBuns(bun);

        assertEquals(2 * BUN_PRICE, burger.getPrice());
    }

    @Test
    @DisplayName("getReceipt формирует чек с булочкой, ингредиентами и итоговой ценой")
    void getReceiptShouldReturnFormattedReceipt() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(BUN_PRICE);
        when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        when(sauce.getName()).thenReturn("hot sauce");
        when(sauce.getPrice()).thenReturn(SAUCE_PRICE);
        when(filling.getType()).thenReturn(IngredientType.FILLING);
        when(filling.getName()).thenReturn("cutlet");
        when(filling.getPrice()).thenReturn(FILLING_PRICE);
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        String expectedReceipt = String.format(
                "(==== black bun ====)%n"
                        + "= sauce hot sauce =%n"
                        + "= filling cutlet =%n"
                        + "(==== black bun ====)%n"
                        + "%nPrice: %f%n",
                2 * BUN_PRICE + SAUCE_PRICE + FILLING_PRICE);

        assertEquals(expectedReceipt, burger.getReceipt());
    }
}
