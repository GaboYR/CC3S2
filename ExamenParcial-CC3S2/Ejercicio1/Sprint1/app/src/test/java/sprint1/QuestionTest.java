package sprint1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
public class QuestionTest {
    
    public boolean answerInRange1to4(int answer) {
        return answer >= 1 && answer <= 4;
    }
    @Test
    public void testAnswerInRange1to4() {
        assertEquals(answerInRange1to4(1), true);
        assertEquals(answerInRange1to4(2), true);
        assertEquals(answerInRange1to4(3), true);
        assertEquals(answerInRange1to4(4), true);
        assertEquals(answerInRange1to4(0), false);
        assertEquals(answerInRange1to4(5), false);
    }
    
    
}
