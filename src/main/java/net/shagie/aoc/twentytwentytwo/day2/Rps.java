package net.shagie.aoc.twentytwentytwo.day2;

public enum Rps {
    ROCK(1, "A", "X"),
    PAPER(2, "B", "Y"),
    SCISSORS(3, "C", "Z");

    final int score;
    final String they;
    final String me;

    Rps(int score, String they, String me) {
        this.score = score;
        this.they = they;
        this.me = me;
    }

    static Wld beats(Rps them, Rps us) {
        if (them == us) {
            return Wld.DRAW;
        } else {
            return (them == ROCK && us == PAPER) ||
                    (them == PAPER && us == SCISSORS) ||
                    (them == SCISSORS && us == ROCK) ? Wld.WIN : Wld.LOSE;
        }
    }
}
