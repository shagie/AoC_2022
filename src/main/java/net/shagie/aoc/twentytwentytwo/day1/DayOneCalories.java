package net.shagie.aoc.twentytwentytwo.day1;

import net.shagie.aoc.twentytwentytwo.util.AOC;
import net.shagie.aoc.twentytwentytwo.util.AOCMarker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.IntStream;

@Component("day1")
@AOC(day = 1)
@SuppressWarnings("java:S106")  // System.out.println is fine
public class DayOneCalories implements AOCMarker {
    @Value("#{resourceReader.readFileToString('classpath:day1/input.txt')}")
    String txt;

    @Override
    public void run(String... args) {
        int[] results = Arrays.stream(txt.split("\n\n"))
                .map(s -> Arrays.stream(s.split("\n")))
                .map(stringStream -> stringStream.mapToInt(Integer::valueOf).sum())
                .flatMapToInt(IntStream::of)
                .sorted()
                .toArray()
                ;
        System.out.println(results[results.length -1]);
        System.out.println(results[results.length -1] + results[results.length -2] + results[results.length -3]);
    }
}
