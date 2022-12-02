package net.shagie.aoc.twentytwentytwo.day2;

import net.shagie.aoc.twentytwentytwo.util.AOC;
import net.shagie.aoc.twentytwentytwo.util.AbstractAOCDay;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component("day2rps")
@AOC(day = 2)
@SuppressWarnings("java:S106")  // System.out.println is fine
public class DayTwoRockPaperScissors extends AbstractAOCDay {
    @Value("#{resourceReader.readFileToString('classpath:day2/data.txt')}")
    String txt;

    @Override
    public void partOne() {
        record ERound(Rps t, Rps u) {
        }

        Map<String, Rps> lookup = new HashMap<>();
        for (Rps value : Rps.values()) {
            lookup.put(value.me, value);
            lookup.put(value.they, value);
        }

        int score = Arrays.stream(txt.split("\n"))
                .map(s -> s.split(" "))
                .map(a -> new ERound(lookup.get(a[0]), lookup.get(a[1])))
                .mapToInt(er -> er.u.score + Rps.beats(er.t, er.u).score)
                .sum();
        System.out.println(score);
    }

    // The Elf finishes helping with the tent and sneaks back over to you.
    // "Anyway, the second column says how the round needs to end:
    //   X means you need to lose,
    //   Y means you need to end the round in a draw, and
    //   Z means you need to win.
    //   Good luck!"
    @Override
    public void partTwo() {
        Map<Rps, Function<String, Rps>> flookup = new EnumMap<>(Rps.class);

        flookup.put(Rps.PAPER, s -> switch (s) {
            case "X" -> Rps.ROCK;
            case "Y" -> Rps.PAPER;
            case "Z" -> Rps.SCISSORS;
            default -> Rps.ROCK;
        });
        flookup.put(Rps.ROCK, s -> switch (s) {
            case "X" -> Rps.SCISSORS;
            case "Y" -> Rps.ROCK;
            case "Z" -> Rps.PAPER;
            default -> Rps.ROCK;
        });
        flookup.put(Rps.SCISSORS, s -> switch (s) {
            case "X" -> Rps.PAPER;
            case "Y" -> Rps.SCISSORS;
            case "Z" -> Rps.ROCK;
            default -> Rps.ROCK;
        });

        Map<String, Rps> rlookup = new HashMap<>();
        for (Rps value : Rps.values()) {
            rlookup.put(value.they, value);
        }

        record Round(Rps them, Rps us) {
        }

        int score = Arrays.stream(txt.split("\n"))
                .map(l -> l.split(" "))
                .map(a -> new Round(rlookup.get(a[0]),
                        flookup.get(rlookup.get(a[0])).apply(a[1])
                ))
                .mapToInt(er -> er.us.score + Rps.beats(er.them, er.us).score)
                .sum();
        System.out.println(score);
    }
}
