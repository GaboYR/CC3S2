package practicacalificada4.cc3s2;

public class Tower {
    private int damage;
    private int range;
    private int fireRate; // turnos entre disparos
    // Constructores, getters y setters
    public Tower(int damage, int range, int fireRate) {
        this.damage = damage;
        this.range = range;
        this.fireRate = fireRate;
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public int getRange() {
        return range;
    }
    public void setRange(int range) {
        this.range = range;
    }
    public int getFireRate() {
        return fireRate;
    }
    public void setFireRate(int fireRate) {
        this.fireRate = fireRate;
    }
}

class CannonTower extends Tower {
    public CannonTower() {
        super(50, 2, 3); // daño, alcance, velocidad de disparo
    }
}
class LaserTower extends Tower {
    public LaserTower() {
        super(100, 3, 5); // daño, alcance, velocidad de disparo
    }
}
class ArrowTower extends Tower {
    public ArrowTower() {
        super(25, 4, 1); // daño, alcance, velocidad de disparo
    }
}