package net.shagie.aoc.twentytwentytwo.day5;

import net.shagie.aoc.twentytwentytwo.util.AOC;
import net.shagie.aoc.twentytwentytwo.util.AOCDay;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("day5stacks")
@AOC(day = 5)
@SuppressWarnings("java:S106")  // System.out.println is fine
public class Day5Stacks implements AOCDay {
    @Value("#{resourceReader.readFileToLoLoS('classpath:day5/data.txt')}")
    List<List<String>> txt;

    @Override
    public void partOne() {
        List<String> moves = txt.get(1);
        List<Deque<Character>> stacks = getStacks(txt.get(0));

        for (String line : moves) {
            String[] parts = line.split(" ");
            int count = Integer.parseInt(parts[1]);
            int from = Integer.parseInt(parts[3]);
            int to = Integer.parseInt(parts[5]);

            var fromStack = stacks.get(from - 1);
            var toStack = stacks.get(to - 1);

            for (int c = 0; c < count; c++) {
                toStack.push(fromStack.pop());
            }
        }

        for (var stack : stacks) {
            System.out.print(stack.peekFirst());
        }
        System.out.println();
    }

    private static List<Deque<Character>> getStacks(List<String> init) {
        String[] sc = init.get(init.size() - 1).trim().split("\\s+");
        int stackCount = sc.length;

        List<Deque<Character>> stacks = new ArrayList<>(stackCount);
        for (int i = 0; i < stackCount; i++) {
            stacks.add(i, new LinkedList<>());
        }

        ListIterator<String> li = init.listIterator(init.size());   // start at one back from last position
        while (li.hasPrevious()) {
            String line = li.previous();
            for (int pos = 0; pos < stackCount; pos++) {
                int col = 1 + pos * 4;
                if (line.length() >= col) {
                    Character p = line.charAt(1 + pos * 4);
                    if (!p.equals(' ')) {
                        stacks.get(pos).push(p);
                    }
                }
            }
        }
        return stacks;
    }

    @Override
    public void partTwo() {
        List<String> moves = txt.get(1);
        List<Deque<Character>> stacks = getStacks(txt.get(0));

        for (String line : moves) {
            String[] parts = line.split(" ");
            int count = Integer.parseInt(parts[1]);
            int from = Integer.parseInt(parts[3]);
            int to = Integer.parseInt(parts[5]);

            var fromStack = stacks.get(from - 1);
            var toStack = stacks.get(to - 1);

            var holding = new LinkedList<Character>();
            for (int c = 0; c < count; c++) {
                holding.push(fromStack.pop());
            }
            for (int c = 0; c < count; c++) {
                toStack.push(holding.pop());
            }
        }

        for (var stack : stacks) {
            System.out.print(stack.peekFirst());
        }
        System.out.println();
    }
}
