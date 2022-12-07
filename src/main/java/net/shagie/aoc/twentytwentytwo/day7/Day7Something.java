package net.shagie.aoc.twentytwentytwo.day7;

import net.shagie.aoc.twentytwentytwo.util.AOC;
import net.shagie.aoc.twentytwentytwo.util.AOCDay;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@AOC(day = 7)
@Component
@SuppressWarnings("java:S106")  // System.out.println is fine
public class Day7Something implements AOCDay {
    @Value("#{resourceReader.readFileToLoS('classpath:day7/data.txt')}")
    List<String> txt;

    @Override
    public void partOne() {
        Deque<String> path = new LinkedList<>();

        Node root = new Node("/", null);
        root.parent = null;

        Node current = root;

        for (String line : txt) {
            String[] args = line.split(" ");
            if (line.startsWith("$")) {
                // cmd
                if (args[1].equals("cd")) {
                    switch (args[2]) {
                        case "/" -> current = root;
                        case ".." -> current = current.parent;
                        default -> current = current.subs.get(args[2]);
                    }
                } else {
                    // ls
                }
            } else {
                // output
                if (args[0].equals("dir")) {
                    current.subs.put(args[1], new Node(args[1], current));
                } else if (args[0].matches("^\\d+$")) {
                    current.files.add(Long.parseLong(args[0]));
                } else {
                    System.out.println("Unknown line: " + line);
                }
            }
        }

        Map<String, Long> dirSize = new LinkedHashMap<>();
        descend(root, dirSize);

        System.out.println(dirSize.values().stream().mapToLong(Long::longValue).sum());
        System.out.println(sums); // DEBUG - this is the right answer
    }

    static long sums;   // I feel dirty
    static Map<String, Long> heres = new LinkedHashMap<>();

    static void descend(Node node, Map<String, Long> data) {
        final long atMost = 100000L;
        long here = node.size();
        heres.put(node.path, here);
        if (here < atMost) {
            data.put(node.path, here);
            System.out.print("* ");
            sums += here;
        } else {
            System.out.print("  ");
        }

        System.out.println(node.path + ": " + here);


        for (Node sub : node.subs.values()) {
            descend(sub, data);
        }
    }

    static class Node {
        String path;
        Map<String, Node> subs;
        List<Long> files;
        Node parent;

        public Node(String path, Node parent) {
            this.path = path;
            this.parent = parent;
            this.subs = new LinkedHashMap<>();
            this.files = new LinkedList<>();
        }

        long size() {
            return subs.values().stream().mapToLong(Node::size).sum() +
                    files.stream().mapToLong(Long::longValue).sum();
        }
    }

    @Override
    public void partTwo() {
        // depends on part 1.

        long avail = 70_000_000L - heres.get("/");
        System.out.println("avail: " + avail);  // 25623268
        long needed = 30_000_000L - avail;
        heres.put("needed", needed);
        heres.put("there", 30_000_000L);

        heres.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(e ->
                        System.out.println(e.getValue() + "\t" + e.getKey())
                );
        // eyeballed - not proud, tired.
    }
}
