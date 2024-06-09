package pc3postfecha;

public class Game {
    private IWordSelector wordSelector;
    private IHintGenerator hintGenerator;
    //private DependencyInjector dependencyInjector;
    private static final int ATTEMPS = 5; 
    // Contructor con inyeccion de dependencias
    public Game(DependencyInjector dependencyInjector) {
        dependencyInjector.injectDependencies(this);
    }
    // Metodos para inyectar dependencias
    public void setWordSelector(IWordSelector wordSelector) {
        this.wordSelector = wordSelector;
    }
    public void setHintGenerator(IHintGenerator hintGenerator) {
        this.hintGenerator = hintGenerator;
    }
    // Metodo para obtener la palabra seleccionada
    public IWordSelector getWordSelector() {
        return wordSelector;
    }
    // Metodo para obtener la pista generada
    public IHintGenerator getHintGenerator() {
        return hintGenerator;
    }
    // Metodo para iniciar el juego
    public void start() {
        String word = wordSelector.selectWord();
        String hint = hintGenerator.generateHint(word);
        
        System.out.println("Palabra : " + hint);
        for (int i = 0; i < ATTEMPS; i++) {
            System.out.println("Intento " + (i + 1));
            String guess = System.console().readLine();
            try {
                if (guess.length() != word.length()) {
                    throw new Exception("La palabra debe tener " + word.length() + " letras.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
            if (guess.equals(word)) {
                System.out.println("Felicidades!");
                return;
            }
            else {
                hintGenerator.viewCharactersInCorrectPosition(word, guess);
                hintGenerator.viewCharacterInIncorrectPosition(word, guess);
                System.out.println("Intenta de nuevo :(");
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("Adivina la palabra");
        // Inyeccion de dependencias
        Game game = new Game(new DependencyInjector());
        game.start();
    }
}
