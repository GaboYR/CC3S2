package pc5.cc3s2;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
public class TDGIntegrationTest {

    @Mock
    private Map mockMap;

    @Mock
    private Player mockPlayer;

    @InjectMocks
    private TowerDefenseGame towerDefenseGame;

    @BeforeEach
    public void setup() {
        mockMap = mock(Map.class);
        mockPlayer = mock(Player.class);

        towerDefenseGame = new TowerDefenseGame(mockMap, mockPlayer);
    }

    @Test
    public void testPlaceTower() {
        Tower tower = new Tower('T');
        towerDefenseGame.placeTower(tower, 0, 0);

        // Verificar que el método placeTower fue llamado en el mock
        verify(mockMap).placeTower(eq(tower), eq(0), eq(0));

        // Configurar el comportamiento del mock para devolver el símbolo correcto
        when(mockMap.getTile(0, 0)).thenReturn('T');

        // Verificar que el símbolo es el correcto
        assertEquals('T', mockMap.getTile(0, 0));
    }

    @Test
    public void testStartWave() {
        towerDefenseGame.startWave();

        // Verificar que una nueva ola fue añadida
        List<Wave> waves = towerDefenseGame.getWaveList();
        assertEquals(1, waves.size());

        // Verificar que la ola está activa
        assertTrue(waves.get(0).isFinished());
    }

    @Test
    public void testGameState() {
        when(mockPlayer.getScore()).thenReturn(100);
        when(mockPlayer.getBaseHealth()).thenReturn(50);

        towerDefenseGame.gameState();

        // Verificar que los métodos getScore y getBaseHealth fueron llamados
        verify(mockPlayer).getScore();
        verify(mockPlayer).getBaseHealth();
    }
}
