package pc5.cc3s2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TowerTest {

    @Test
    public void testTowerConstructorWithValidSymbols() {
        // Precondición: El símbolo debe pertenecer a 'T', 'C', 'L', 'A', 'S'
        char[] validSymbols = {'T','C', 'L', 'A', 'S'};

        for (char symbol : validSymbols) {
            Tower tower = new Tower(symbol);
            // Postcondición: El símbolo de la torre debe ser el dado previamente
            assertEquals(symbol, tower.getSymbol(), "El símbolo no coincide");
        }
    }

    @Test
    public void testTowerConstructorWithInvalidSymbol() {
        // Precondición: El símbolo no pertenece a las clases válidas
        char invalidSymbol = 'X';

        Tower tower = new Tower(invalidSymbol);
        // Postcondición: Simbolo invalido no debe ser aceptado
        assertNotEquals('T', tower.getSymbol(), "El símbolo no coincide");
    }
}
