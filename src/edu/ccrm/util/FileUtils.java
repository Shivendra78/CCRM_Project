package edu.ccrm.util;

import java.io.*;
import java.nio.file.*;
import java.util.stream.*;

public class FileUtils {

    public static Stream<String> readLines(String path) throws IOException {
        return Files.lines(Paths.get(path));
    }

    public static void writeLines(String path, Stream<String> lines) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path))) {
            lines.forEach(line -> {
                try {
                    writer.write(line);
                    writer.newLine();
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
        }
    }
}
