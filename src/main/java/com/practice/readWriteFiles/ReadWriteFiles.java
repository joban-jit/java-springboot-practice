package com.practice.readWriteFiles;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ReadWriteFiles {


    public static void main(String[] args) throws IOException {
        Path dir = Path.of("demo/test/");
//        Path file = dir
        createDirectory(dir); //Created directory: C:\Users\Username\Documents\StudyProjects\Intellij_Workspace\java-springboot-practice\demo
        createDirectory(Path.of("demo/backup")); //Created directory: C:\Users\Username\Documents\StudyProjects\Intellij_Workspace\java-springboot-practice\demo
        createDirectory(Path.of("demo/archive")); //Created directory: C:\Users\Username\Documents\StudyProjects\Intellij_Workspace\java-springboot-practice\demo
        Path filePath = Path.of("demo/test/example.txt");
        createFile(filePath);
        String content = """
                Hello world!,
                How are you doing?
                Hope you are doing good.""";
        // Writing to file
        // commented below as I have already written to file
//        writeStringToFile(content, filePath);
        byte[] bytes = "ki hall aa?.".getBytes();
//        writingBytesToFile(bytes);

        // Reading from file
//        readStringFromFile(filePath);
//        readUsingReadAllLinesMethod(filePath);
        readLargeFiles(filePath);
//        readBytes();
//        deleteIfExists(filePath);
//        copyAndRenameFiles(filePath, Path.of("demo/backup/copied.txt"));
//        moveAndRenameFiles(Path.of("demo/backup/copied.txt"), Path.of("demo/archive/copied.txt"));


    }

    public static void createDirectory(Path dir) throws IOException {

        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
            System.out.println("Created directory: "+dir.toAbsolutePath());
        }
    }
    public static void createFile(Path filePath) throws IOException {
        if(!Files.exists(filePath)){
            Files.createFile(filePath);
        }
    }

    public static void writeStringToFile(String content, Path filePath) throws IOException {
        Files.writeString(filePath,
                content,
                StandardOpenOption.APPEND);
    }

    public static void writingBytesToFile(byte[] bytes) throws IOException {
        Path path = Path.of("demo/test/binary.bin");
        if(!Files.exists(path)){
            Files.createFile(path);
        }
        Files.write(path, bytes, StandardOpenOption.APPEND);
    }



    public static void readStringFromFile(Path filePath) throws IOException {
        // use this when
        //The file is plain text
        //The file is not too large (under ~50–100 MB)
        //You want the whole content in one String
        String content = Files.readString(filePath);
        System.out.println(content);
        //Hello world!,
        //How are you doing?
        //Hope you are doing good.

    }

    public static void readUsingReadAllLinesMethod(Path filePath) throws IOException {
        // use this when
        //The file is plain text
        //You want the file line by line, but already loaded in memory
        //File is reasonably small (full file is loaded)
        List<String> lines = Files.readAllLines(filePath);
        lines.forEach(System.out::println);
        //Hello world!,
        //How are you doing?
        //Hope you are doing good.

    }

    public static void readLargeFiles(Path filePath) throws IOException {
        // use this when
        //The file may be large
        //You want to stream lines instead of loading everything
        //You want functional operations (filter, map, etc.)
        // This does not load the whole file in memory.
        try(Stream<String> stream = Files.lines(filePath)){
            stream.forEach(System.out::println);
            //Hello world!,
            //How are you doing?
            //Hope you are doing good.
//            boolean exists = stream.anyMatch(s -> s.contains("doing"));
//            System.out.println(exists); //true
        }


    }

    public static void readBytes() throws IOException {
        // use this when
        //File is binary (images, videos, PDF, ZIP, etc.)
        //OR you need low-level byte processing
        //OR you want maximum speed for reading the entire file
        Path path = Path.of("demo/test/binary.bin");
        byte[] bytes = Files.readAllBytes(path);
        System.out.println(new String(bytes));
        //ki hall aa?.
    }

    public static void deleteIfExists(Path filePath) throws IOException {
        Files.deleteIfExists(filePath);
    }

    public static void copyAndRenameFiles(Path source, Path destination) throws IOException {
        Files.copy(
                source,
                destination
        , StandardCopyOption.REPLACE_EXISTING // use this when you want to overwrite existing
        );
    }

    public static void moveAndRenameFiles(Path source, Path destination) throws IOException {
        Files.move(
                source,
                destination,
                StandardCopyOption.REPLACE_EXISTING
        );
    }
}
