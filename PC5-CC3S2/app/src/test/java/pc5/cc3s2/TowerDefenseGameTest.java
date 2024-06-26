package pc5.cc3s2;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class TowerDefenseGameTest {
    
    @Mock
    private Map mockMap;

    @Mock
    private Player mockPlayer;

    @Mock
    private Wave mockWave;

    @InjectMocks
    private TowerDefenseGame towerDefenseGame;

    @BeforeEach
    public void setup() {
        mockMap = mock(Map.class);
        mockPlayer = mock(Player.class);
        mockWave = mock(Wave.class);
        towerDefenseGame = new TowerDefenseGame(mockMap, mockPlayer);
    }
    // Test para verificar torre en (0, 0)
    // Usando mocks para simular el mapa y el jugador
    @Test
    public void testPlaceTower() {
        Tower tower = new Tower('T');
        towerDefenseGame.placeTower(tower, 0, 0);
        verify(mockMap).placeTower(eq(tower), eq(0), eq(0));
        when(mockMap.getTile(0, 0)).thenReturn('T');
        assertEquals('T', mockMap.getTile(0, 0));
    }
}
