package pc5.cc3s2;
public class Tower {
    private char symbol;

    /**
     * Construye una clase torre con tipo de torre.
     * 
     * @param symbol es el simbolo, para este caso se cuenta con Cannon, Laser, Arrow y SniperTower
     */
    public Tower(char symbol) {
        this.symbol = symbol;
    }

    /**
     * Retorna el simbolo que representa la torre.
     * 
     * @return el simbolo de la torre
     */
    public char getSymbol() {
        return symbol;
    }
}