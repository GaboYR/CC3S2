package sprint3;
public class DependencyInjector {
    public static IHintGenerator hintGenerator() {
        return new HintGenerator();
    }
    public static IWordSelector wordSelector() {
        return new WordSelector();
    }
}