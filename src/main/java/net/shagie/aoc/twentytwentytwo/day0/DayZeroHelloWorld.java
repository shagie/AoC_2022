package net.shagie.aoc.twentytwentytwo.day0;

import net.shagie.aoc.twentytwentytwo.util.AOC;
import net.shagie.aoc.twentytwentytwo.util.AOCMarker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("day0")
@AOC(day = 0)
@SuppressWarnings("java:S106")  // System.out.println is fine
public class DayZeroHelloWorld implements AOCMarker {
    @Value("#{resourceReader.readFileToString('classpath:day0/hello.txt')}")
    String txt;

    @Override
    public void run(String... args) {
        System.out.println(txt);
    }
}
