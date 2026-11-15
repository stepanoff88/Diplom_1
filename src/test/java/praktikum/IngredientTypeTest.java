package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IngredientTypeTest {

    @Test
    @DisplayName("Перечисление содержит только соус и начинку")
    void shouldContainOnlySauceAndFilling() {
        assertArrayEquals(
                new IngredientType[]{IngredientType.SAUCE, IngredientType.FILLING},
                IngredientType.values());
    }

    @ParameterizedTest(name = "Тип {0} находится по своему имени")
    @EnumSource(IngredientType.class)
    @DisplayName("valueOf возвращает тип ингредиента по имени")
    void valueOfShouldReturnTypeByName(IngredientType type) {
        assertEquals(type, IngredientType.valueOf(type.name()));
    }
}
