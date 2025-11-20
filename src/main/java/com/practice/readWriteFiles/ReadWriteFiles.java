package com.practice.readWriteFiles;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadWriteFiles {


    public static void main(String[] args) throws IOException {
        Path dir = Path.of("demo");
//        Path file = dir
        createDirectory(dir);
    }

    public static void createDirectory(Path dir) throws IOException {

        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
            System.out.println("Created directory: "+dir.toAbsolutePath());
        }


    }
}
