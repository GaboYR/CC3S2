package com.wordz.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WordTest {
    @Test
    public void oneIncorrectLetter() {
        var word = new Word("A");
        var score = word.guess("Z");
        var result = score.letter(0);
        assertThat(result).isEqualTo(Letter.INCORRECT);
    }
    // Completa...
    public void oneCorrectLetter() {
        var word = new Word("A");
        var score = word.guess("A");
        var result = score.letter(0);
        assertThat(result).isEqualTo(Letter.CORRECT);
    }
    public void onePartCorrectLetter() {
        var word = new Word("AB");
        var score = word.guess("AC");
        var result = score.letter(0);
        assertThat(result).isEqualTo(Letter.CORRECT);
        result = score.letter(1);
        assertThat(result).isEqualTo(Letter.PART_CORRECT);
    }
    @Test
    public void wordHasFiveLetters() {
        var word = new Word();
        var result = word.getWord();
        assertThat(result.length()).isEqualTo(5);
    }
    @Test
    public void wordWhoHasNotFiveLettersShowException() {
        var word = new Word("AB");
        try {
            word.getWord();
        } catch (RuntimeException e) {
            assertThatThrownBy(
                () -> Word.class.getConstructor(String.class)
            ).isInstanceOf(RuntimeException.class).hasMessage("Palabra de long 5"   );
        }
    }
}
