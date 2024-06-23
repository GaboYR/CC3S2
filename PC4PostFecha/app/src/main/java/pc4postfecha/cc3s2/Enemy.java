package pc4postfecha.cc3s2;

public class Enemy {
    private int speed; // número de celdas por turno
    private int health;
    private int reward;
    // Constructores, getters y setters
    public Enemy(int speed, int health, int reward) {
        this.speed = speed;
        this.health = health;
        this.reward = reward;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        this.health = health;
    }
    public int getReward() {
        return reward;
    }
    public void setReward(int reward) {
        this.reward = reward;
    }
    public void takeDamage(int damage) {
        health -= damage;
    }
}

class BasicEnemyFactory implements EnemyFactory {
    @Override
    public Enemy createEnemy() {
        return new Enemy(1, 100, 10);
    }
}
class BossEnemyFactory implements EnemyFactory {
    @Override
    public Enemy createEnemy() {
        return new Enemy(1, 200, 50);
    }
}


class BasicEnemy extends Enemy {
    public BasicEnemy() {
        super(1, 100, 10);
    }
}
class FastEnemy extends Enemy {
    public FastEnemy() {
        super(2, 50, 20); 
    }
}
class BossEnemy extends Enemy {
    public BossEnemy() {
        super(1, 200, 50);
    }
}
// Clase SpeedyEnemy
class SpeedyEnemy extends Enemy {
    public SpeedyEnemy() {
        super(3, 75, 30);
    }
}