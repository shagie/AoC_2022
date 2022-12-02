package net.shagie.aoc.twentytwentytwo.day1;

import net.shagie.aoc.twentytwentytwo.util.AOC;
import net.shagie.aoc.twentytwentytwo.util.AOCDay;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Component("day1supplier")
@AOC(day = 1)
@SuppressWarnings("java:S106")  // System.out.println is fine
public class DayOneCalories implements AOCDay {
    @Value("#{resourceReader.readFileToLoLoS('classpath:day1/input.txt')}")
    List<List<String>> txt;

    Supplier<Stream<Integer>> soFar = () -> txt.stream().map(l -> l.stream().mapToInt(Integer::valueOf).sum());

    @Override
    public void partOne(String... args) {
        System.out.println(soFar.get().reduce(0, Integer::max));
    }

    @Override
    public void partTwo(String... args) {
        System.out.println(soFar.get()
                .sorted(Collections.reverseOrder())
                .limit(3)
                .mapToInt(Integer::intValue)
                .sum());
    }
}
