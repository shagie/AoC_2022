package net.shagie.aoc.twentytwentytwo.day3;

import net.shagie.aoc.twentytwentytwo.util.AOC;
import net.shagie.aoc.twentytwentytwo.util.AbstractAOCDay;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AOC(day = 3)
@Component("day3sack")
@SuppressWarnings("java:S106")  // System.out.println is fine
public class Day3Rucksack extends AbstractAOCDay {
    @Value("#{resourceReader.readFileToLoS('classpath:day3/data.txt')}")
    List<String> txt;

    @Override
    public void partOne() {
        record StrSack(String a, String b) {
        }
        record SetStack(Set<String> a, Set<String> b) {
        }

        int answer = txt.stream()
                .map(l -> new StrSack(
                        l.substring(0, l.length() / 2),
                        l.substring(l.length() / 2)))
                .map(strSack -> new SetStack(
                        new HashSet<>(Arrays.asList(strSack.a.split(""))),
                        new HashSet<>(Arrays.asList(strSack.b.split("")))
                ))
                .map(setStack -> {
                    Set<String> data = new HashSet<>(setStack.a);
                    data.retainAll(setStack.b);
                    return data.iterator().next().charAt(0);
                })
                .mapToInt(c -> (c >= 'a') ? (c - 'a' + 1) : 26 + (c - 'A' + 1))
                .sum();
        System.out.println(answer);
    }
}
