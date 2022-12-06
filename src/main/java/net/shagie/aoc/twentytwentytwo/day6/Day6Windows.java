package net.shagie.aoc.twentytwentytwo.day6;

import net.shagie.aoc.twentytwentytwo.util.AOC;
import net.shagie.aoc.twentytwentytwo.util.AOCDay;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component("day6windows")
@AOC(day = 6)
@SuppressWarnings("java:S106")  // System.out.println is fine
public class Day6Windows implements AOCDay {
    @Value("#{resourceReader.readFileToString('classpath:day6/data.txt')}")
    String txt;

    @Override
    public void partOne() {
        for (int start = 0; start < txt.length() - 4; start++) {
            int end = start + 4;
            long count = Arrays.stream(txt.substring(start, end).split("")).distinct().count();
            if (count == 4) {
                System.out.println(end);
                break;
            }
        }
    }

    @Override
    public void partTwo() {
        for (int start = 0; start < txt.length() - 14; start++) {
            int end = start + 14;
            long count = Arrays.stream(txt.substring(start, end).split("")).distinct().count();
            if (count == 14) {
                System.out.println(end);
                break;
            }
        }
    }
}
