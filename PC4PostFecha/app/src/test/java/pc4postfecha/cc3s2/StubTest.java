package pc4postfecha.cc3s2;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.mockito.Mock;
import org.mockito.Mockito;

import org.junit.jupiter.api.Test;

public class StubTest {
    @Mock
    Tower tower;
    @Mock
    Enemy mockEnemy;
    @Mock
    Tower game;

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

    @Test
    // ¿Cómo usarías stubs en Mockito para simular el comportamiento del método getEnemies en
    //la clase Wave durante las pruebas?
    public void testGetEnemies() {
        // Configurar mock para el método getEnemies
        Wave mockWave = Mockito.mock(Wave.class);
        when(mockWave.getEnemies()).thenReturn(new ArrayList<Enemy>());
        // Verificar que el método getEnemies fue llamado
        verify(mockWave).getEnemies();
    }

    @Test
    //Implementa un test que use un stub para verificar el comportamiento del método startWave
    //en la clase TowerDefenseGame.
    public void testStartWave() {
        // Configurar mock para el método startWave
        
        Tower mockGame = Mockito.mock(Tower.class);
        when(mockGame.startWave()).thenReturn(true);
        // Verificar que el método startWave fue llamado
        verify(mockGame).startWave();
    }

}
