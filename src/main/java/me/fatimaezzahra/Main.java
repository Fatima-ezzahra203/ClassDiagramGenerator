package me.fatimaezzahra;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Set;
import java.util.stream.Collectors;

import static me.fatimaezzahra.Utilities.*;

public class Main {

    public static void main(String[] args) throws MalformedURLException {
        final String pack = getPackage();
        String path = "";
        File directory;

        do {
            path = getPath();
            directory = getDirectory(path);
            if (directory == null)
                path = null;
        } while (path == null || path.isBlank());

        URL[] urls = new URL[]{ directory.toURI().toURL() };

        final ClassLoader loader = new URLClassLoader(urls);

        final Set<Class<?>> classes = getFiles(path).stream()
                .filter(File::isFile)
                .filter(file -> file.getName().endsWith(".class"))
                .map(file -> {
                    final String clazz = pack + "." + file.getName().split("\\.")[0];


                    try {
                        return loader.loadClass(clazz);
                    } catch (ClassNotFoundException e) {
                        System.out.println("Could not load class: " + clazz);
                        System.out.println("Wrong package maybe?");
                        return null;
                    }

                }).collect(Collectors.toSet());

        final DiagramGenerator generator = new DiagramGenerator(classes);
        final String data = generator.generateDiagram();
        System.out.println(data);
        saveToFile("sample.md", data);
    }

}
