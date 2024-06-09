package pc3postfecha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class WordSelectorTest {
    @Test
    public void testGetWord() {
        WordSelector wordSelector = new WordSelector();
        assertEquals("example", wordSelector.getWord());
    }
    @Test
    public void testGetHint() {
        WordSelector wordSelector = new WordSelector();
        assertEquals("example", wordSelector.getHint());
    }
    @Test
    public void testSelectWord() {
        WordSelector wordSelector = new WordSelector();
        assertEquals("example", wordSelector.selectWord());
    }
}
