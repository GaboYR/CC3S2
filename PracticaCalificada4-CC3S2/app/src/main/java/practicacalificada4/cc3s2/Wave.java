package practicacalificada4.cc3s2;

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
        // Primero ubicar los enemigos en el mapa
        // Buscar en el mapa los '1' y guardar las posiciones
        for (char i = 0; i < mapa.mapa.length; i++) {
            for (char j = 0; j < mapa.mapa[i].length; j++) {
                if (mapa.mapa[i][j] == '1') {
                    positionEnemies.add(new Point(i, j));
                }
            }
        }
        // Mover los enemigos
        Point base = mapa.getBase();
        // Si su coordenada x no es igual a la base, mover a la derecha
        for (Point p : positionEnemies) {
            if (p.x != base.x) {
                p.x++;
            }
        }
        // use enemies
        enemies.forEach(enemy -> enemy.move());
        //use waveNumber
        
    }
    public int getWaveNumber() {
        return waveNumber;
    }
}   