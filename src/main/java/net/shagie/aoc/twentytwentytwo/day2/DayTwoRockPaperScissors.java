package net.shagie.aoc.twentytwentytwo.day2;

import net.shagie.aoc.twentytwentytwo.util.AOC;
import net.shagie.aoc.twentytwentytwo.util.AbstractAOCDay;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component("day2rps")
@AOC(day = 2)
@SuppressWarnings("java:S106")  // System.out.println is fine
public class DayTwoRockPaperScissors extends AbstractAOCDay {
    @Value("#{resourceReader.readFileToLoS('classpath:day2/data.txt')}")
    List<String> txt;

    @Override
    public void partOne() {
        record Round(Rps t, Rps u) {
        }

        int score = txt.stream()
                .map(s -> s.split(" "))
                .map(a -> new Round(Rps.abcxyz2Rps(a[0]), Rps.abcxyz2Rps(a[1])))
                .mapToInt(er -> er.u.score + Rps.beats(er.t, er.u).score)
                .sum();
        System.out.println(score);
    }

    @Override
    public void partTwo() {
        Map<Rps, Function<Wld, Rps>> flookup = new EnumMap<>(Rps.class);

        flookup.put(Rps.PAPER, s -> switch (s) {
            case LOSE -> Rps.ROCK;
            case DRAW -> Rps.PAPER;
            case WIN -> Rps.SCISSORS;
        });
        flookup.put(Rps.ROCK, s -> switch (s) {
            case LOSE -> Rps.SCISSORS;
            case DRAW -> Rps.ROCK;
            case WIN -> Rps.PAPER;
        });
        flookup.put(Rps.SCISSORS, s -> switch (s) {
            case LOSE -> Rps.PAPER;
            case DRAW -> Rps.SCISSORS;
            case WIN -> Rps.ROCK;
        });

        record Round(Rps them, Rps us) {
        }

        int score = txt.stream()
                .map(l -> l.split(" "))
                .map(a -> new Round(Rps.abcxyz2Rps(a[0]),
                        flookup.get(Rps.abcxyz2Rps(a[0])).apply(Wld.xyz2Wld((a[1])))
                ))
                .mapToInt(er -> er.us.score + Rps.beats(er.them, er.us).score)
                .sum();
        System.out.println(score);
    }
}
