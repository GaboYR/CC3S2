package main.java;
public class Main {
    public static void main(String[] args) {
        var word = new Word("Apple");
        var score = word.guess("Apple");
        System.out.println(score);
    }
}
