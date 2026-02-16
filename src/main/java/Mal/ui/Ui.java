package Mal.ui;

import java.util.ArrayList;
import java.util.Scanner;
import Mal.task.Task;

/**
 * Handles interactions with the user, primarily managing input and output formatting.
 */
public class Ui {
    private static final String LINE = "_______________________________________________";
    private static final String WELCOME_MESSAGE = "Hello I'm Mal\nYou summoned me?";
    private static final String EXIT_MESSAGE = "Finally. I thought you'd never stop talking. Stay rotten.";

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the next line of input from the user.
     * @return String of user input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void showWelcome() {
        printWithLines(WELCOME_MESSAGE);
    }

    public void showAdded(Task task, int size) {
        String message = "Added:\n" + task
                + "\nNow you have " + size + " tasks for world domination";
        printWithLines(message);
    }

    public void showBye() {
        printWithLines(EXIT_MESSAGE);
    }

    public static void showError(String msg) {
        printWithLines(msg);
    }

    public void showHelp(ArrayList<String> commands) {
        if (commands == null || commands.isEmpty()) {
            return;
        }

        System.out.println("Oops you messed up! Let me help\nPerhaps you meant:");
        for (String command : commands) {
            System.out.println(command);
        }
    }

    public void showLoadingError() {
        printWithLines("LOADING ERROR: The archives are incomplete.");
    }

    // --- Private Helper Methods (SLAP Improvements) ---

    /**
     * Helper method to wrap a message in horizontal lines.
     * This ensures consistent formatting across all UI outputs.
     */
    private static void printWithLines(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }
}