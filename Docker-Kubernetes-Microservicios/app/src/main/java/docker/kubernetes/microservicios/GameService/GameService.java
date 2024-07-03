package docker.kubernetes.microservicios.GameService;

import docker.kubernetes.microservicios.MapService.MapService;
import docker.kubernetes.microservicios.PlayerService.PlayerService;
import docker.kubernetes.microservicios.TowerService.TowerService;
import docker.kubernetes.microservicios.WaveService.WaveService;

import java.util.*;

public class GameService {
    private MapService mapService;
    private PlayerService playerService;
    private List<WaveService> waves;

    public GameService() {
        this.mapService = new MapService();
        this.playerService = new PlayerService();
        this.waves = new ArrayList<>();
    }

    public void placeTower(TowerService tower, int x, int y) {
        mapService.placeTower(tower, x, y);
    }

    public void startWave() {
        WaveService wave = new WaveService();
        waves.add(wave);
        wave.start();
    }

    public void gameState() {
        System.out.println(mapService);
        System.out.println("Puntuación: " + playerService.getScore());
        System.out.println("Vida de la base: " + playerService.getBaseHealth());
    }
    public static void main(String[] args) {
        System.out.println("Bienvenido al juego de torres!");
    }
}