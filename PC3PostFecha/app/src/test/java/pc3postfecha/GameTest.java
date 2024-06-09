package pc3postfecha;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game(new DependencyInjector());
        game.setWordSelector(null);
        game.setHintGenerator(null);
    }

    @Test
    public void testGetWord() {
        String word = game.getWordSelector().selectWord();
        assertNotNull(word);
        assertFalse(word.isEmpty());
    }

    @Test
    public void testGetHintWithNullWord() {
        String hint = game.getHintGenerator().generateHint("example");
        assertEquals("", hint);
    }

    @Test
    public void testGetHintWithEmptyWord() {
        String hint = game.getHintGenerator().generateHint(null);
        assertEquals("", hint);
    }

    @Test
    public void testGetHintWithWord() {
        String hint = game.getHintGenerator().generateHint(null);
        assertEquals("_ _ _ _ _ _ _ ", hint);
    }
}
