package docker.kubernetes.microservicios;

import docker.kubernetes.microservicios.MapService.*;
import docker.kubernetes.microservicios.PlayerService.*;
import docker.kubernetes.microservicios.TowerService.*;
import docker.kubernetes.microservicios.GameService.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

public class GameServiceTest {
    @Mock
    private MapService mockMapService;
    @Mock
    private PlayerService mockPlayerService;
    @InjectMocks
    private GameService gameService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPlaceTower() {
        TowerService mockTower = mock(TowerService.class);
        gameService.placeTower(mockTower, 2, 2);
        verify(mockMapService).placeTower(mockTower, 2, 2);
    }
}