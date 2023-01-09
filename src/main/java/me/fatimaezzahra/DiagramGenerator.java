package me.fatimaezzahra;

import me.fatimaezzahra.annotations.ChildClass;
import me.fatimaezzahra.annotations.ParentClass;

import java.lang.reflect.Modifier;
import java.util.*;

public record DiagramGenerator(Set<Class<?>> classes) {

    public String generateDiagram() {
        final Map<String, List<String>> hierarchy = new HashMap<>();

        classes.forEach(clazz -> {

            if (clazz.getAnnotation(ParentClass.class) != null) {
                if (!hierarchy.containsKey(clazz.getSimpleName()))
                    hierarchy.put(clazz.getSimpleName(), new ArrayList<>());
            } else if (clazz.getAnnotation(ChildClass.class) != null) {
                final String parentClass = clazz.getAnnotation(ChildClass.class)
                        .parentClass();

                if (hierarchy.containsKey(parentClass))
                    hierarchy.get(parentClass).add(clazz.getSimpleName());
                else {
                    hierarchy.put(parentClass, new ArrayList<>());
                    hierarchy.get(parentClass).add(clazz.getSimpleName());
                }
                System.out.println(clazz.getSimpleName());
            }
        });

        hierarchy.forEach((key, value) -> System.out.println("KEY: " + key + " VALUE: " + value));
        return generateMermaidText(hierarchy);
    }

    private String generateMermaidText(Map<String, List<String>> hierarchy) {
        final StringBuilder result = new StringBuilder();
        result.append("```mermaid\nclassDiagram\n");

        hierarchy.forEach((key, list) -> list.forEach(child -> result.append(key)
                .append(" <|-- ")
                .append(child)
                .append("\n")));

        classes.forEach(clazz -> {
            result.append("class ")
                    .append(clazz.getSimpleName())
                    .append(" {\n");
            if (clazz.isInterface())
                result.append("<<interface>>\n");

            Arrays.stream(clazz.getDeclaredFields())
                    .forEach(field -> result.append(getAccessModifier(Modifier.toString(field.getModifiers())))
                            .append(field.getType().getSimpleName())
                            .append(" ")
                            .append(field.getName())
                            .append("\n"));

            Arrays.stream(clazz.getDeclaredMethods()).forEach(method -> {
                result.append(getAccessModifier(Modifier.toString(method.getModifiers())))
                        .append(method.getName())
                        .append("()\n");
            });
            result.append("}\n");
        });

        return result.append("```").toString();
    }

    private char getAccessModifier(String string) {
        return switch (string) {
            case "public" -> '+';
            case "private" -> '-';
            case "protected" -> '#';
            default -> '~';
        };
    }
}
