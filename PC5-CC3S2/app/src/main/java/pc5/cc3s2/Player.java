package pc5.cc3s2;

public class Player {
    private int score;
    private int baseHealth;

    public Player() {
        this.score = 0;
        this.baseHealth = 100;
    }

    public int getScore() {
        return score;
    }

    public int getBaseHealth() {
        return baseHealth;
    }
}