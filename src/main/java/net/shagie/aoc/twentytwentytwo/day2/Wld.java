package net.shagie.aoc.twentytwentytwo.day2;

public enum Wld {
    WIN(6),
    DRAW(3),
    LOSE(0);

    final int score;

    Wld(int score) {
        this.score = score;
    }

    //   X means you need to lose,
    //   Y means you need to end the round in a draw, and
    //   Z means you need to win.

    public static Wld xyz2Wld(String xyz) {
        return switch (xyz) {
            case "X" -> Wld.LOSE;
            case "Y" -> Wld.DRAW;
            case "Z" -> Wld.WIN;
            default -> null;
        };
    }
}
