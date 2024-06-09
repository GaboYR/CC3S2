package pc3postfecha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class HintGeneratorTest {
    @Test
    public void testGenerateHintWithEmptyWord() {
        HintGenerator hintGenerator = new HintGenerator();
        String hint = hintGenerator.generateHint("");
        assertEquals("", hint);
    }  // hintGenerator.viewCharactersInCorrectPosition(word, guess);
    @Test
    public void testViewCharactersInCorrectPositionWithDifferentWords() {
        HintGenerator hintGenerator = new HintGenerator();
        hintGenerator.viewCharactersInCorrectPosition("example", "exempla");
    }
    @Test
    public void testViewCharactersInCorrectPositionWithEmptyWord() {
        HintGenerator hintGenerator = new HintGenerator();
        hintGenerator.viewCharactersInCorrectPosition("", "");
    }
    @Test
    public void testViewCharactersInCorrectPositionWithNullWord() {
        HintGenerator hintGenerator = new HintGenerator();
        hintGenerator.viewCharactersInCorrectPosition(null, null);
    }
    @Test
    public void testViewCharacterInIncorrectPositionWithDifferentWords() {
        HintGenerator hintGenerator = new HintGenerator();
        hintGenerator.generateHint("example");
        hintGenerator.viewCharacterInIncorrectPosition("example", "exempla");
    }
     @Test
    public void testGenerateHint() {
        HintGenerator hintGenerator = new HintGenerator();
        String word = "example";
        String hint = hintGenerator.generateHint(word);
        assertEquals("_ _ _ _ _ _ _ ", hint);
    }

    @Test
    public void testGenerateHintEmptyWord() {
        HintGenerator hintGenerator = new HintGenerator();
        String word = "";
        String hint = hintGenerator.generateHint(word);
        assertEquals("", hint);
    }

    @Test
    public void testViewCharactersInCorrectPosition() {
        HintGenerator hintGenerator = new HintGenerator();
        String word = "example";
        String userWord = "exxxxxx";
        
        // Capture the output of the method
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        hintGenerator.viewCharactersInCorrectPosition(word, userWord);
        
        // Restore the original System.out
        System.setOut(System.out);
        
        String expectedOutput = "e x _ _ _ _ _ \n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testViewCharacterInIncorrectPosition() {
        HintGenerator hintGenerator = new HintGenerator();
        String word = "gato";
        String userWord = "hola";
        
        // Capture the output of the method
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        hintGenerator.viewCharacterInIncorrectPosition(word, userWord);
        
        // Restore the original System.out
        System.setOut(System.out);
        
        String expectedOutput = "Letras correctas : a o \n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testViewCharacterInIncorrectPositionNoMatch() {
        HintGenerator hintGenerator = new HintGenerator();
        String word = "gato";
        String userWord = "miel";
        
        // Capture the output of the method
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        hintGenerator.viewCharacterInIncorrectPosition(word, userWord);
        
        // Restore the original System.out
        System.setOut(System.out);
        
        String expectedOutput = "Letras correctas : \n";
        assertEquals(expectedOutput, outContent.toString());
    }
}
