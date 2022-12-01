package net.shagie.aoc.twentytwentytwo.day1;

import net.shagie.aoc.twentytwentytwo.util.AOC;
import net.shagie.aoc.twentytwentytwo.util.AOCMarker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component("day1")
@AOC(day = 1)
@SuppressWarnings("java:S106")  // System.out.println is fine
public class DayOneCalories implements AOCMarker {
    @Value("#{resourceReader.readFileToString('classpath:day1/input.txt')}")
    String txt;

    @Override
    public void run(String... args) {
        List<Integer> sums = new ArrayList<>();
        for(String chunk : txt.split("\n\n")) {
            sums.add(Arrays.stream(chunk.split("\n"))
                    .mapToInt(Integer::parseInt)
                    .sum());
        }
        Collections.sort(sums);
        System.out.println(sums.get(sums.size() - 1));
        System.out.println(sums.get(sums.size() - 1) +
                sums.get(sums.size() - 2) + sums.get(sums.size() - 3));
    }
}
