package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IngredientTest {

    @ParameterizedTest(name = "Тип ингредиента: {0}")
    @EnumSource(IngredientType.class)
    @DisplayName("Конструктор сохраняет тип ингредиента")
    void constructorShouldStoreType(IngredientType type) {
        Ingredient ingredient = new Ingredient(type, "cutlet", 100.0f);

        assertEquals(type, ingredient.getType());
    }

    @ParameterizedTest(name = "{0}: \"{1}\" за {2}")
    @CsvSource({
            "SAUCE, hot sauce, 100",
            "FILLING, cutlet, 200",
            "SAUCE, '', 0"
    })
    @DisplayName("Конструктор сохраняет название и цену ингредиента")
    void constructorShouldStoreNameAndPrice(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals(name, ingredient.getName());
        assertEquals(price, ingredient.getPrice());
    }
}
