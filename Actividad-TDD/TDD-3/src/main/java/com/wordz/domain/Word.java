package com.wordz.domain;

import java.util.ArrayList;
import java.util.List;
public class Word {
    private final String word;
    public Word(String correctWord) {
        this.word = correctWord;
    }
    public Word() {
        this.word = randomWord();
    }
    public String getWord() {
        return word;
    }
    public Score guess(String attempt) {
        var score = new Score(word);
        score.assess(0, attempt);
        return score;
    }
    public String randomWord() {
        List<String> words = new ArrayList<>();
        words.add("Apple");
        words.add("Bread");
        words.add("Chair");
        words.add("Dream");
        words.add("Earth");
        words.add("Flute");
        words.add("Glass");
        words.add("Heart");
        words.add("Light");
        words.add("Money");
        words.add("Noble");
        words.add("Peace");

        return words.get((int) (Math.random() * words.size()));
    }
}
