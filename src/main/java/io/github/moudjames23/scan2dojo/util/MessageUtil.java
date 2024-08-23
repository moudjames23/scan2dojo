package io.github.moudjames23.scan2dojo.util;

public class MessageUtil {

    // Codes de couleur ANSI
    public static final String RESET = "\u001B[0m";

    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";

    public static void printlnWithBorder(String color, String message) {
        String border = "--------------------------------------------------------------------------------";
        System.out.println(color + border);
        System.out.println(message);
        System.out.println(border + RESET);
    }
}
