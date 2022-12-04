package net.shagie.aoc.twentytwentytwo.day4;

import net.shagie.aoc.twentytwentytwo.util.AOC;
import net.shagie.aoc.twentytwentytwo.util.AOCDay;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AOC(day = 4)
@Component("day4overlap")
public class Day4Overlap implements AOCDay {
    @Value("#{resourceReader.readFileToLoS('classpath:day4/data.txt')}")
    List<String> txt;

    @Override
    public void partOne() {
        int count = 0;

        for (String line : txt) {
            String[] segments = line.split(",");
            int zs = Integer.parseInt(segments[0].split("-")[0]);
            int ze = Integer.parseInt(segments[0].split("-")[1]);
            Set<Integer> zero = new HashSet<>();
            for (int i = zs; i <= ze; i++) {
                zero.add(i);
            }

            int os = Integer.parseInt(segments[1].split("-")[0]);
            int oe = Integer.parseInt(segments[1].split("-")[1]);
            Set<Integer> one = new HashSet<>();
            for (int i = os; i <= oe; i++) {
                one.add(i);
            }

            Set<Integer> zro = new HashSet(zero);
            zro.retainAll(one);

            Set<Integer> orz = new HashSet(one);
            orz.retainAll(zero);

            if (zro.size() == one.size() || orz.size() == zero.size()) {
                count += 1;
            }
        }
        System.out.println(count);
    }

    @Override
    public void partTwo() {
        int count = 0;

        for (String line : txt) {
            String[] segments = line.split(",");
            int zs = Integer.parseInt(segments[0].split("-")[0]);
            int ze = Integer.parseInt(segments[0].split("-")[1]);
            Set<Integer> zero = new HashSet<>();
            for (int i = zs; i <= ze; i++) {
                zero.add(i);
            }

            int os = Integer.parseInt(segments[1].split("-")[0]);
            int oe = Integer.parseInt(segments[1].split("-")[1]);
            Set<Integer> one = new HashSet<>();
            for (int i = os; i <= oe; i++) {
                one.add(i);
            }

            Set<Integer> zro = new HashSet(zero);
            zro.retainAll(one);
            count += zro.size() > 0 ? 1 : 0;
        }
        System.out.println(count);
    }
}
