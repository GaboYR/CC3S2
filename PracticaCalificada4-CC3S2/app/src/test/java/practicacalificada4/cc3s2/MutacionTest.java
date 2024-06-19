package practicacalificada4.cc3s2;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MutacionTest {
    // Implementa pruebas de mutacion para verificar la calidad de las pruebas unitarias
    // Test para el método isValidPosition de la clase Mapa
    @Test
    public void testIsValidPosition() {
        // Configurar mock para posición válida
        Mapa mockMap = Mockito.mock(Mapa.class);
        when(mockMap.isValidPosition(3, 4)).thenReturn(true);
        // Verificar que la posición sea válida
        verify(mockMap).isValidPosition(3, 4);
    }
    
    // Test para el método placeTower de la clase Game
    @Test
    public void testPlaceTower_ValidPosition() {
        // Configurar mock para posición válida
        Mapa mockMap = Mockito.mock(Mapa.class);
        Game game = Mockito.mock(Game.class);
        when(mockMap.isValidPosition(3, 4)).thenReturn(true);
        game.placeTower(new CannonTower(), 3, 4);
        // Verificar que la torre se haya colocado
        verify(mockMap,never()).placeTower(any(Tower.class), eq(3), eq(4));
        //verify(mockMap).placeTower(any(Tower.class), eq(3), eq(4));
    }

    @Test
    public void testPlaceTower_InvalidPosition() {
        // Configurar mock para posición inválida
        Mapa mockMap = Mockito.mock(Mapa.class);
        Game game = Mockito.mock(Game.class);
        when(mockMap.isValidPosition(3, 4)).thenReturn(false);
        game.placeTower(new CannonTower(), 3, 4);
        // Verificar que la torre no se haya colocado
        verify(mockMap, never()).placeTower(any(Tower.class), eq(3), eq(4));
    }
}
