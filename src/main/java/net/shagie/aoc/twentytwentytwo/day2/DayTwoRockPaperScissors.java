package net.shagie.aoc.twentytwentytwo.day2;

import net.shagie.aoc.twentytwentytwo.util.AOC;
import net.shagie.aoc.twentytwentytwo.util.AbstractAOCDay;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
}
