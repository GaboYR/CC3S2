package docker.kubernetes.microservicios.TowerService;

public class TowerService {
    private char symbol;
    public TowerService(char symbol) {
    this.symbol = symbol;
    }
    public char getSymbol() {
    return symbol;
    }
}