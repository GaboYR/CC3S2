package sprint2;
public class Game {
    //private final WordSelector wordSelector;
    private final HintGenerator hintGenerator;
    private final String word;
    private final String hint;
    private int attempts;
    public Game(WordSelector wordSelector, HintGenerator hintGenerator) {
        //this.wordSelector = wordSelector;
        this.hintGenerator = hintGenerator;
        this.word = wordSelector.selectWord();
        this.hint = hintGenerator.generateHint(word);
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
                hintGenerator.generateHint(word, guess);
                System.out.println("La palabra es: " + hintGenerator.generateHint(word, guess));
                System.out.println("¡Palabra incorrecta! Te quedan " + attempts + " intentos");
            }
        }
        System.out.println("¡Has perdido! La palabra era: " + word);
    }
    public static void main(String[] args) {
        System.out.println("Adivina la palabra secreta");
        System.out.println("Tienes 5 intentos");
        WordSelector wordSelector = new WordSelector();
        HintGenerator hintGenerator = new HintGenerator();
        Game game = new Game(wordSelector, hintGenerator);
        game.start();
    }
}   
