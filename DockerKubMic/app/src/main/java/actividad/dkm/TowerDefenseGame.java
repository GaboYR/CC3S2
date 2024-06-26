package actividad.dkm;

import java.util.*;
public class TowerDefenseGame {
    private Mapa map;
    private Player player;
    private List<Wave> waves;

    public TowerDefenseGame() {
        this.map = new Mapa();
        this.player = new Player();
        this.waves = new ArrayList<>();
    }

    public void placeTower(Tower tower, int x, int y) {
        map.placeTower(tower, x, y);
    }

    public void startWave() {
        Wave wave = new Wave();
        waves.add(wave);
        wave.start();
    }
    public void printStatsPlayer(){
        System.out.println("Puntaje: " + player.getScore());
        System.out.println("Vida de la base: " + player.getBaseHealth());
    }
    public static void main(String[] args) {
        System.out.println("Bienvenido a Tower Defense Game");
        TowerDefenseGame game = new TowerDefenseGame();
        game.startWave();
    }
}