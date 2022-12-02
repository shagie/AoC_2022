package net.shagie.aoc.twentytwentytwo.day2;

public enum Wld {
    WIN(6),
    DRAW(3),
    LOSE(0);

    final int score;

    Wld(int score) {
        this.score = score;
    }
}
