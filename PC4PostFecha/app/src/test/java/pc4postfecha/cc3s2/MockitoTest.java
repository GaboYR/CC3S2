package pc4postfecha.cc3s2;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MockitoTest {

    private Tower tower;
    private Enemy mockEnemy;

    private class Game {
        private Mapa map;

        public Game(Mapa map) {
            this.map = map;
        }

        public void placeTower(Tower tower, int x, int y) {
            map.placeTower(tower, x, y);
        }
    }

    @BeforeEach
    @DisplayName("Setup")
    public void setup() {
        tower = new Tower(50, 2, 3);
        mockEnemy = mock(Enemy.class);
    }
    @Test
    @DisplayName("Test torre ataca enemigo")
    public void testTowerAttacksEnemy() {
        
        doNothing().when(mockEnemy).takeDamage(anyInt());
        when(mockEnemy.getHealth()).thenReturn(100);
        tower.attack(mockEnemy);
        verify(mockEnemy).takeDamage(eq(50));
    }

    @Test
    @DisplayName("Test enemigo recibe daño")
    public void testEnemyAfterAttack() {
        when(mockEnemy.getHealth()).thenReturn(100);
        tower.attack(mockEnemy);
        verify(mockEnemy).takeDamage(eq(50));
        assertEquals(100, mockEnemy.getHealth());
    }

    @Test
    // Colocar torre en el mapa
    @DisplayName("Test colocar torre en el mapa")
    public void testPlaceTower() {
        Mapa mockMap = mock(Mapa.class);
        Game game = new Game(mockMap);
        when(mockMap.isValidPosition(3, 4)).thenReturn(true);
        game.placeTower(new CannonTower(), 3, 4);
        verify(mockMap).placeTower(any(Tower.class), eq(3), eq(4));
    }

    @Test
    @DisplayName("Test colocar torre en posición inválida")
    public void testPlaceTowerInvalidPosition() {
        Mapa mockMap = mock(Mapa.class);
        Game game = new Game(mockMap);
        when(mockMap.isValidPosition(3, 4)).thenReturn(false);
        game.placeTower(new CannonTower(), 3, 4);
        verify(mockMap, never()).placeTower(any(Tower.class), eq(3), eq(4));
    }
}
