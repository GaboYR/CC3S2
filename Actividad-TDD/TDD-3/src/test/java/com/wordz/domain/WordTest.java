package com.wordz.domain;

import org.junit.jupiter.api.Test;

import static com.wordz.domain.Letter.*;
import static org.assertj.core.api.Assertions.assertThat;

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
    
}
