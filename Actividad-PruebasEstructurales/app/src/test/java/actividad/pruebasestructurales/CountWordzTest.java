package actividad.pruebasestructurales;
import actividad.pruebasestructurales.CountWords;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;


class CountWordzTest {
    @Test
    void twoWordsEndingWithS() { // 1
        int words = new CountWords().count("dogs cats");
        assertThat(words).isEqualTo(2);
    }

    @Test
    void noWordsAtAll() { // 2
        int words = new CountWords().count("dog cat");
        assertThat(words).isEqualTo(0);
    }
}
