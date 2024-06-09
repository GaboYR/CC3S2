package pc3postfecha;
// Clase para manejar la inyeccion de dependencias
public class DependencyInjector {
    // Metodo para inyectar dependencias
    public void injectDependencies(Game game) {
        game.setWordSelector(new WordSelector());
        game.setHintGenerator(new HintGenerator());
    }
    // Metodo para obtener la instancia de DependencyInjector
    public static DependencyInjector getDependencyInjector() {
        return new DependencyInjector();
    }
}