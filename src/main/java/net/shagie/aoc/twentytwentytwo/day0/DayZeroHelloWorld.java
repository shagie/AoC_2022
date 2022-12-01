package net.shagie.aoc.twentytwentytwo.day0;

import net.shagie.aoc.twentytwentytwo.util.AOC;
import net.shagie.aoc.twentytwentytwo.util.AOCDay;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component("day0")
@AOC(day = 0)
@SuppressWarnings("java:S106")  // System.out.println is fine
public class DayZeroHelloWorld implements AOCDay {
    @Value("#{resourceReader.readFileToString('classpath:day0/hello.txt')}")
    String txt;

    @Override
    public void partOne(String... args) {
        System.out.println(txt);
    }

    @Override
    public void partTwo(String... args) {
        System.out.println(txt.toLowerCase(Locale.US));
    }
}
