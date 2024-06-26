package sprint3;

public class Game {
    private IHintGenerator hintGenerator;
    private final String word;
    private final String hint;
    private int attempts;
    public Game(IWordSelector wordSelector, IHintGenerator hintGenerator) {
        this.hintGenerator = hintGenerator;
        this.word = wordSelector.selectWord();
        this.hint = hintGenerator.generateHString(word);
        this.attempts = 5;
        System.out.println("La palabra es: " + hint);
    }
    public void start() {
        while (attempts > 0) {
            System.out.println("Intenta adivinar la palabra");
            String guess = System.console().readLine();
            if (guess.equals(word)) {
                System.out.println("¡Has adivinado la palabra!");
                return;
            } else {
                attempts--;
                hintGenerator.generateHString(word, guess);
                System.out.println("La palabra es: " + hintGenerator.generateHString(word, guess));
                System.out.println("¡Palabra incorrecta! Te quedan " + attempts + " intentos");
            }
        }
        System.out.println("¡Has perdido! La palabra era: " + word);
    }
    public static void main(String[] args) {
        System.out.println("Adivina la palabra secreta");
        System.out.println("Tienes 5 intentos");
        IWordSelector wordSelector = DependencyInjector.wordSelector();
        IHintGenerator hintGenerator = DependencyInjector.hintGenerator();
        Game game = new Game(wordSelector, hintGenerator);
        game.start();
    }
}   