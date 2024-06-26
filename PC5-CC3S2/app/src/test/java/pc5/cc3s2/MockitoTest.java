package pc5.cc3s2;

public class MockitoTest {

    private Tower tower;
    private Enemy mockEnemy;

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
}