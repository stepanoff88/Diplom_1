package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BunTest {

    @ParameterizedTest(name = "Булочка \"{0}\" стоит {1}")
    @CsvSource({
            "black bun, 100",
            "white bun, 200",
            "red bun, 300",
            "'', 0"
    })
    @DisplayName("Конструктор сохраняет название и цену булочки")
    void constructorShouldStoreNameAndPrice(String name, float price) {
        Bun bun = new Bun(name, price);

        assertEquals(name, bun.getName());
        assertEquals(price, bun.getPrice());
    }
}
