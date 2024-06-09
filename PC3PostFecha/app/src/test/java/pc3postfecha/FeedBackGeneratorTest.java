package pc3postfecha;
//package test.java.pc3postfecha;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import pc3postfecha.FeedBackGenerator;
public class FeedBackGeneratorTest {
    // Prueba para verificar que se genere un feedback correcto
    @Test
    public void testGenerateFeedback() {
        FeedBackGenerator feedBackGenerator = new FeedBackGenerator();
        assertEquals("Correcto!", feedBackGenerator.generateFeedback(true));
        assertEquals("Incorrecto!", feedBackGenerator.generateFeedback(false));
    }
    // Prueba para verificar que se muestre un feedback
    @Test
    public void testViewFeedback() {
        FeedBackGenerator feedBackGenerator = new FeedBackGenerator();
        feedBackGenerator.viewFeedback("Correcto!");
    }
}
