package com.wordz.domain;

import org.junit.jupiter.api.Test;

import static com.wordz.domain.Letter.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WordTest {
    @Test 
    public void oneIncorrectLetter() {
        Word word = new Word("A");
        assertThat(word.tryLetter(A)).isFalse();
        assertThat(word.getWord()).isEqualTo("Z");
    }
    
}
