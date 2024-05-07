package com.wordz.domain;

import java.util.ArrayList;
public class Score {
    private final String correct;
    private Letter resultado = Letter.INCORRECT ;
    public Score(String correct) {
        this.correct = correct;
    }
    public Letter letter(int position) {
        return resultado;
    }
    public void assess(int position, String attempt) {
        if (correct.charAt(position) == attempt.
                charAt(position)) {
            resultado = Letter.CORRECT;
        }
        else {
            resultado = Letter.INCORRECT;
            ArrayList<Character> correctChars = new ArrayList<>();
            for (char c : correct.toCharArray()) {
                correctChars.add(c);
            }
        }
    }
}
