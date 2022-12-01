package net.shagie.aoc.twentytwentytwo.util;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component("resourceReader")
public class ResourceReader {
    public static String readFileToString(String path) throws IOException {
        return FileUtils.readFileToString(ResourceUtils.getFile(path), StandardCharsets.UTF_8);
    }
}
