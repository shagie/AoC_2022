package net.shagie.aoc.twentytwentytwo.day2;

public enum Rps {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);

    final int score;

    Rps(int score) {
        this.score = score;
    }

    static Rps abcxyz2Rps(String abc) {
        return switch (abc) {
            case "A", "X" -> ROCK;
            case "B", "Y" -> PAPER;
            case "C", "Z" -> SCISSORS;
            default -> ROCK;    // Poor predictable Bart
        };
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
