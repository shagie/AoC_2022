package net.shagie.aoc.twentytwentytwo.util;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Component("resourceReader")
@SuppressWarnings("unused") // used via SPEL
public class ResourceReader {
    private ResourceReader() {
    }

    public static String readFileToString(String path) throws IOException {
        return FileUtils.readFileToString(ResourceUtils.getFile(path), StandardCharsets.UTF_8);
    }

    public static List<List<String>> readFileToLoLoS(String path) throws IOException {
        return Arrays.stream(FileUtils.readFileToString(ResourceUtils.getFile(path), StandardCharsets.UTF_8)
                        .split("\n\n"))
                .map(chunk -> List.of(chunk.split("\n")))
                .toList();
    }

    public static List<String> readFileToLoS(String path) throws IOException {
        return Arrays.stream(FileUtils.readFileToString(ResourceUtils.getFile(path), StandardCharsets.UTF_8)
                        .split("\n"))
                .toList();
    }
}
