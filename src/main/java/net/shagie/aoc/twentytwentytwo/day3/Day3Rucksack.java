package net.shagie.aoc.twentytwentytwo.day3;

import net.shagie.aoc.twentytwentytwo.util.AOC;
import net.shagie.aoc.twentytwentytwo.util.AOCDay;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@AOC(day = 3)
@Component("day3sack")
@SuppressWarnings("java:S106")  // System.out.println is fine
public class Day3Rucksack implements AOCDay {
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

    @Override
    public void partTwo() {
        List<List<String>> groups = ListUtils.partition(txt, 3);
        int answer = groups.stream()
                .mapToInt(los -> {
                    Map<Character, Integer> counts = new LinkedHashMap<>();
                    los.stream()
                            .map(s -> Arrays.stream(s.split(""))
                                    .distinct()
                                    .map(st -> st.charAt(0))
                                    .toList()
                            )
                            .forEach(loc -> loc.forEach(c ->
                                    counts.put(c, counts.getOrDefault(c, 0) + 1)
                            ));
                    char key = counts.entrySet().stream().filter(entry -> entry.getValue() == 3)
                            .findFirst()
                            .map(Map.Entry::getKey)
                            .orElse(' ');
                    return (key >= 'a') ? (key - 'a' + 1) : 26 + (key - 'A' + 1);
                })
                .sum();
        System.out.println(answer);
    }
}
