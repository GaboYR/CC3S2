package pc4postfecha.cc3s2;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class d {

    private Enemy mockEnemy;
    private Tower cannonTower;

    @BeforeEach
    public void setup() {
        mockEnemy = mock(Enemy.class);
        cannonTower = new CannonTower();
    }

    @Test
    public void testCannonTowerAttacksEnemy() {
        // Configurar el mock para el método takeDamage
        doNothing().when(mockEnemy).takeDamage(anyInt());

        // Atacar al enemigo
        cannonTower.attack(mockEnemy);

        // Verificar que el método takeDamage fue llamado con el daño correcto
        verify(mockEnemy).takeDamage(eq(50));
    }

    @Test
    public void testEnemyHealthAfterAttack() {
        // Configurar el mock para el método getHealth
        when(mockEnemy.getHealth()).thenReturn(100);

        // Atacar al enemigo
        cannonTower.attack(mockEnemy);

        // Verificar que el método takeDamage fue llamado
        verify(mockEnemy).takeDamage(eq(50));

        // Verificar la salud del enemigo después del ataque
        assertEquals(100, mockEnemy.getHealth()); // Esta verificación es redundante ya que estamos mockeando el método getHealth
    }

    @Test
    public void testEnemyTakesDamage() {
        // Crear un enemigo real
        Enemy enemy = new BasicEnemy();

        // Atacar al enemigo
        cannonTower.attack(enemy);

        // Verificar que la salud del enemigo se reduce
        assertEquals(50, enemy.getHealth());
    }
}
