package Mal.ui;

import java.util.Scanner;
import java.util.ArrayList;
import Mal.task.*;


public class Ui {
    private final static String LINE = "_______________________________________________";
    private Scanner sc = new Scanner(System.in);

    public String readCommand() { return sc.nextLine(); }
    public void showLine() { System.out.println(LINE); }

    public void showWelcome() {
        System.out.println(LINE + "\nHello I'm Mal\nYou summoned me?\n" + LINE);
    }

    public void showAdded(Task t, int size) {
        System.out.println(LINE + "\nAdded:\n" + t + "\nNow you have " + size + " tasks for world domination\n" + LINE);
    }

    public void showBye() {
        System.out.println(LINE + "\nFinally. I thought you'd never stop talking. Stay rotten.\n" + LINE);
    }

    public static void showError(String msg) {
        System.out.println(LINE + "\n" + msg + "\n" + LINE);
    }

    public void showHelp(ArrayList<String> commands) {
        System.out.println("Oops you messed up! Let me help\nPerhaps you meant:");
        for (String c : commands) System.out.println(c);
    }

    public void showLoadingError() {
        System.out.println("LOADING ERROR");
    }
}
