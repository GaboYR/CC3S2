package pc4postfecha.cc3s2;

import java.util.ArrayList;
import java.util.List;
public class Wave {
    private List<Enemy> enemies;
    private int waveNumber;
    private List<Point> positionEnemies;
    public Mapa mapa;
    public Wave(int waveNumber) {
        this.waveNumber = waveNumber;
        this.enemies = generateEnemies(waveNumber);
    }

    private List<Enemy> generateEnemies(int waveNumber) {
        List<Enemy> enemies = new ArrayList<>();
        for (int i = 0; i < waveNumber * 5; i++) { // más enemigos cada oleada
            enemies.add(new BasicEnemy());
        }
        if (waveNumber % 5 == 0) { // jefe cada 5 oleadas
            enemies.add(new BossEnemy());
        }
        return enemies;
    }
    // Métodos para manejar el progreso de la oleada
    public void startWave() {
        waveNumber ++;
        for (char i = 0; i < mapa.mapa.length; i++) {
            for (char j = 0; j < mapa.mapa[i].length; j++) {
                if (mapa.mapa[i][j] == '1') {
                    positionEnemies.add(new Point(i, j));
                }
            }
        }
        
    }
    public List<Enemy> getEnemies() {
        return enemies;
    }
    public int getWaveNumber() {
        return waveNumber;
    }
    public void setWaveNumber(int waveNumber) {
        this.waveNumber = waveNumber;
    }
    public List<Point> getPositionEnemies() {
        return positionEnemies;
    }
    public void setPositionEnemies(List<Point> positionEnemies) {
        this.positionEnemies = positionEnemies;
    }
    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }
    
}   