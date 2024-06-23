package pc4postfecha.cc3s2;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TowerDefenseGameTest {

    private EnemyFactory mockEnemyFactory;
    private TowerFactory mockTowerFactory;

    @BeforeEach
    public void setup() {
        mockEnemyFactory = mock(EnemyFactory.class);
        mockTowerFactory = mock(TowerFactory.class);
    }

    @Test
    public void testCreateBasicEnemy() {
        when(mockEnemyFactory.createEnemy()).thenReturn(new BasicEnemy());
        Enemy enemy = mockEnemyFactory.createEnemy();
        assertTrue(enemy instanceof BasicEnemy);
        assertEquals(100, enemy.getHealth());
    }

    @Test
    public void testCreateBossEnemy() {
        when(mockEnemyFactory.createEnemy()).thenReturn(new BossEnemy());
        Enemy enemy = mockEnemyFactory.createEnemy();
        assertTrue(enemy instanceof BossEnemy);
        assertEquals(200, enemy.getHealth());
    }

    @Test
    public void testCreateCannonTower() {
        when(mockTowerFactory.createTower()).thenReturn(new CannonTower());
        Tower tower = mockTowerFactory.createTower();
        assertTrue(tower instanceof CannonTower);
        assertEquals(50, tower.getDamage());
        assertEquals(2, tower.getRange());
    }

    @Test
    public void testCreateLaserTower() {
        when(mockTowerFactory.createTower()).thenReturn(new LaserTower());
        Tower tower = mockTowerFactory.createTower();
        assertTrue(tower instanceof LaserTower);
        assertEquals(100, tower.getDamage());
        assertEquals(3, tower.getRange());
    }
}
