package pc5.cc3s2;


import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TowerDefenseGameTest {
    // Mock para la clase TowerDefenseGame
    @Mock
    private TowerDefenseGame towerDefenseGame;
    // Mock para la clase Map
    @Mock
    private Map mockMap;
    // Mock para la clase Player
    @Mock
    private Player player;
    // Mock para la clase Wave
    @Mock
    private Wave wave;
    
    @BeforeEach
    public void setUp() {
        towerDefenseGame = new TowerDefenseGame();
        mockMap = Mockito.mock(Map.class);
        player = Mockito.mock(Player.class);
        wave = Mockito.mock(Wave.class);
    }

    // Pruebas para map
    @Test
    public void testPlaceTower() {
        Tower tower = new Tower('T');
        towerDefenseGame.placeTower(tower, 0, 0);
        verify(mockMap,never()).placeTower(eq(tower), eq(0), eq(0));
    }
    // Pruebas para player
    @Test
    public void testGameState() {
        when(player.getScore()).thenReturn(0);
        when(player.getBaseHealth()).thenReturn(100);
        towerDefenseGame.gameState();
        verify(player,never()).getScore();
        verify(player,never()).getBaseHealth();
    }
    // Pruebas para waves
    @Test
    public void testStartWave() {
        towerDefenseGame.startWave();
        verify(wave,never()).start();
    }
}
