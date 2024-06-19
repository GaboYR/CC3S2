package sprint2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HintGeneratorTest {
    @Test
    void testGenerateHint() {
        HintGenerator hintGenerator = new HintGenerator();
        String word = "casa";
        String hint = hintGenerator.generateHint(word);
        assertNotNull(hint);
        assertEquals("****", hint);
    }
}
