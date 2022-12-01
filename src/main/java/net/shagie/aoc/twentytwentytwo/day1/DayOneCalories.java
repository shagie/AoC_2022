package net.shagie.aoc.twentytwentytwo.day1;

import net.shagie.aoc.twentytwentytwo.util.AOC;
import net.shagie.aoc.twentytwentytwo.util.AOCMarker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Component("day1")
@AOC(day = 1)
@SuppressWarnings("java:S106")  // System.out.println is fine
public class DayOneCalories implements AOCMarker {
    @Value("#{resourceReader.readFileToString('classpath:day1/input.txt')}")
    String txt;

    @Override
    public void run(String... args) {
        Supplier<Stream<Integer>> soFar = () -> Arrays.stream(txt.split("\n\n"))
                .map(s -> Arrays.stream(s.split("\n")))
                .map(stringStream -> stringStream.mapToInt(Integer::valueOf).sum())
                ;

        System.out.println(soFar.get().reduce(0, Integer::max));
        System.out.println(soFar.get()
                .sorted(Collections.reverseOrder())
                .limit(3)
                .mapToInt(Integer::intValue)
                .sum());
    }
}
