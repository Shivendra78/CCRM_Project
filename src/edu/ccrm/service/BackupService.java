package edu.ccrm.service;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.nio.file.attribute.BasicFileAttributes;

public class BackupService{

    private static final String BACKUP_BASE_DIR = "backup";

    public static void backupDataFiles(String... dataFiles) throws IOException{
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Path backupDir = Paths.get(BACKUP_BASE_DIR, "backup_" + timestamp);
        if (!Files.exists(backupDir)) {
            Files.createDirectories(backupDir);
        }

        for (String file : dataFiles) {
            Path source = Paths.get(file);
            if (Files.exists(source)){
                Path target = backupDir.resolve(source.getFileName());
                Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            }
        }
        System.out.println("Backup completed: " + backupDir.toAbsolutePath());
    }

     public static long calculateDirectorySize(Path dir) throws IOException {
        final long[] totalSize = {0};

        Files.walkFileTree(dir, new SimpleFileVisitor<>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                totalSize[0] += attrs.size();
                return FileVisitResult.CONTINUE;
            }
        });
        return totalSize[0];
    }

    public static void printFilesRecursively(Path dir, int depth) throws IOException {
        Files.walkFileTree(dir, new SimpleFileVisitor<>(){
            private int currentDepth = 0;

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                currentDepth++;
                if (currentDepth > depth) {
                    return FileVisitResult.SKIP_SUBTREE;
                } else {
                    System.out.println("Dir: " + dir.toString());
                    return FileVisitResult.CONTINUE;
                }
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                System.out.println("File: " + file.toString());
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
                currentDepth--;
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
