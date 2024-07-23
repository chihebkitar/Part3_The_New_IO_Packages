package newio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FilesDemos {
    public static void main(String[] args) throws IOException {
        //create a directory
        Files.createDirectory(Paths.get("data"));

        //create a directory with intermediate directories
        Files.createDirectories(Paths.get("sub1","sub2","sub3"));

        //add an empty file to that directory
        Files.createFile(Paths.get("sub1","sub2","sub3","myfile.txt"));

        // delete them all
        boolean deleted = Files.deleteIfExists(Paths.get("sub1","sub2","sub3","myfile.txt"));
        deleted = Files.deleteIfExists(Paths.get("data"));
        deleted = Files.deleteIfExists(Paths.get("sub1","sub2","sub3"));
        deleted = Files.deleteIfExists(Paths.get("sub1","sub2"));
        deleted = Files.deleteIfExists(Paths.get("sub1"));

        //access a file read it into a collection and print
        Path sourceDir = Paths.get("src","main","java","newio");
        Path dataFile = sourceDir.resolve("data.txt");
        System.out.println(Files.lines(dataFile).collect(Collectors.toList()));

        // copy the file to new location
        Path destination = sourceDir.resolve("data_copy.txt");
        Files.copy(dataFile,destination);

        // move the file
        Path other = sourceDir.resolve("data_moved.txt");
        Files.move(destination,other);

        //delete the copy
        Files.deleteIfExists(other);

        // visit all files in the source folder
        Path javaDir = Paths.get("src","main","java");
        try(Stream<Path> entries = Files.walk(javaDir)) {
            entries.forEach(System.out::println);
            }


        }

    }
