package practicacalificada4.cc3s2;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.Mockito;

import org.junit.jupiter.api.Test;

public class TowerDefenseGameTest {
    
    @Test
    public void testPlaceTower_ValidPosition() {
        // Configurar mock para posición válida
        Mapa mockMap = Mockito.mock(Mapa.class);
        Game game = Mockito.mock(Game.class);
        when(mockMap.isValidPosition(3, 4)).thenReturn(true);
        game.placeTower(new CannonTower(), 3, 4);
        // Verificar que la torre se haya colocado
        verify(mockMap,never()).placeTower(any(Tower.class), eq(3), eq(4));
    }

    @Test
    public void testPlaceTower_InvalidPosition() {
        Mapa mockMap = Mockito.mock(Mapa.class);
        Game game = Mockito.mock(Game.class);
        // Configurar mock para posición inválida
        when(mockMap.isValidPosition(3, 4)).thenReturn(false);
        game.placeTower(new CannonTower(), 3, 4);
        // Verificar que la torre no se haya colocado
        verify(mockMap, never()).placeTower(any(Tower.class), eq(3), eq(4));
    }
}
