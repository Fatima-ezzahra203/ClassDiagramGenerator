package me.fatimaezzahra;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utilities {

    public static Set<File> getFiles(String path) {
        Path start = Paths.get(path);
        try (Stream<Path> stream = Files.walk(start, Integer.MAX_VALUE)) {

            return stream.map(p -> new File(p.toString())).collect(Collectors.toSet());
        } catch (IOException e) {
            e.printStackTrace();
            return new HashSet<>();
        }
    }

    public static File getDirectory(String path) {
        File file = new File(path);

        if (!file.exists())
            System.out.println("Directory doesn't exist!");
        else if (!file.isDirectory())
            System.out.println("Not a valid directory path!");
        if (!file.exists() || !file.isDirectory())
            file = null;
        return file;
    }

    public static String getPath() {
        final Scanner scanner = new Scanner(System.in);
        String path = "";

        do {
            System.out.println("Enter the path to the java classes:");
            path = scanner.nextLine();
            if (path == null || path.isBlank()) {
                System.out.println("The path cannot be empty!");
            }
        } while (path == null || path.isBlank());
        return path;
    }

    public static String getPackage() {
        final Scanner scanner = new Scanner(System.in);
        String result = "";

        do {
            System.out.println("Enter the package:");
            result = scanner.nextLine();
            if (result == null || result.isBlank()) {
                System.out.println("The package cannot be empty!");
            }
        } while (result == null || result.isBlank());
        return result;
    }

    public static void saveToFile(String fileName, String data) {
        final File file = new File(fileName);

        if (file.exists()) {
            System.out.println("File already exists!");
            return;
        }

        try (final BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(data);
        } catch (IOException e) {
            System.out.println("Could not save data to file: " + fileName + " reason:");
            e.printStackTrace();
        }

    }

}
