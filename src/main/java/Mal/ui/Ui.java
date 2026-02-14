package Mal.ui;

import java.util.ArrayList;
import java.util.Scanner;

import Mal.task.*;

/**
 * The Ui class deals with interactions with the user,
 *
 */

public class Ui {
    private final static String LINE = "_______________________________________________";
    private Scanner sc = new Scanner(System.in);

    /**
     * Reads every line of input
     * @return String. This returns next line of input as a String
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Print out line breaker in between responses
     */
    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * prints the welcome message
     */
    public void showWelcome() {

        System.out.println(LINE + "\nHello I'm Mal\nYou summoned me?\n" + LINE);
    }

    /**
     * Prints out the message after a task a=has been added
     * @param t This is that task that has been added
     * @param size This is the size of the list
     */
    public void showAdded(Task t, int size) {
        System.out.println(LINE + "\nAdded:\n" + t + "\nNow you have " + size + " tasks for world domination\n" + LINE);
    }

    /**
     * prints out the bye message
     */
    public void showBye() {
        System.out.println(LINE + "\nFinally. I thought you'd never stop talking. Stay rotten.\n" + LINE);
    }

    /**
     * Prints out the error message
     * @param msg this is the error message to be shown
     */
    public static void showError(String msg) {

        System.out.println(LINE + "\n" + msg + "\n" + LINE);
    }

    /**
     * Prints out the commands to the user in case they are not aware of the syntax
     * @param commands The list of commands as String
     */
    public void showHelp(ArrayList<String> commands) {
        System.out.println("Oops you messed up! Let me help\nPerhaps you meant:");
        for (String c : commands) {
            System.out.println(c);
        }
    }

    public void showLoadingError() {
        System.out.println("LOADING ERROR");
    }
}
