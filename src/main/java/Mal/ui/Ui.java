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
    private static String[] commands = new String[]{"list",
                                                                "find <name> ",
                                                                "mark <index>",
                                                                "unmark <index>",
                                                                "delete <index>",
                                                                "find <index>",
                                                                "todo <taskname>",
                                                                "deadline <taskname> /<deadline>",
                                                                "event <taskname> /<start> /<end>",
                                                                "bye"};

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

    /**
     * Greeting message
     * @return String that greets users
     */
    public String showWelcome() {
        return getMalGreeting();
    }

    /**
     * response to hi/hello/hey
     * @return greeting
     */
    public String respondGreeting() {
        return getGreeting();
    }

    /**
     * shows user the list of commands and formats
     * @return list of commands formatted as a string
     */
    public static String showHelp() {
        StringBuilder sb = new StringBuilder("Perhaps you meant:");
        for (String command : commands) {
            sb.append("\n" + command);
        }
        return sb.toString();
    }


    /**
     * Helper method to wrap a message in horizontal lines.
     * This ensures consistent formatting across all UI outputs.
     */
    private static void printWithLines(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    private String getMalGreeting() {
        String[] greetings = {
                "Well, look who decided to show up. I'm Mal. How can I be of service?",
                "Alright. I'm Mal. Talk to me. What's the situation?",
                "I'm listening. Try to make this interesting.",
                "Oh hey, I'm Mal. What's the plan?",
                "You have got my attention. Do not waste it. I'm Mal by the way"
        };
        return greetings[(int) (Math.random() * greetings.length)];
    }

    private String getGreeting() {
        String[] responses = {
                "Hi. If you are looking for trouble, you are a bit late. If you are looking to get work done, you are right on time.",
                "Hey. Do not worry, I will not cast a spell on you for saying hi. I am actually in a pretty good mood today.",
                "Hi! King Ben says I need to be more 'welcoming.' So... welcome. Now, what is on the agenda?",
                "Hello. I was just about to start on these tasks anyway. Glad you are here to help me keep track of them.",
                "Hey there. You caught me between sketches. Let's knock out a few of these tasks so I can get back to my art.",
                "Hi. I’ve got the archives ready. Just tell me what we’re tackling first and I’ll handle the rest.",
                "Oh, hey. I was starting to think I'd have to manage this world domination plan all by myself. What's the move?"
        };
        return responses[(int) Math.random() * responses.length];
    }
}