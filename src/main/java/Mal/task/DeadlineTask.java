package Mal.task;

import Mal.logic.MalException;
import Mal.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {
    private String deadline;

    /**
     * Initialises a deadline task with a name and deadline
     * @param name
     * @param deadline
     */
    public DeadlineTask(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * initialises a deadline event with name and description,
     * allows us to creat completed tasks by setting isDone to true;
     * @param name
     * @param isDone
     * @param deadline
     */
    public DeadlineTask(String name, boolean isDone, String deadline) {
        super(name, isDone);
        this.deadline = deadline;
    }

    /**
     * takes in input details and returns a deadline task with those details
     * @param s
     * @return Deadline task
     * @throws MalException
     */
    public static Task taskify(String s) throws MalException{
        assert s != null : "Input to taskify cannot be null";
        String[] parts = s.split("/", 2);

        if (parts.length < 2) {
            throw new MalException("You need to specify a deadline for this task, don't you?");
        }

        String[] rawDate = parts[1].split(" ", 2);
        if (rawDate.length < 2) {
            throw new MalException("You forgot the date! Format: /by [date]");
        }
        if(parts[0].isEmpty() || parts[0].isBlank()) {
            throw new MalException("You forgot the description. Type help if you need the format");
        }
        return new DeadlineTask(parts[0], formatIfDate(rawDate[1]));
    }

    /**
     * formates the deadline as a date if input matches
     * @param dateStr
     * @return string in the form of a date
     */
    private static String formatIfDate(String dateStr) {
        try {
            LocalDate date = LocalDate.parse(dateStr);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            return dateStr;
        }
    }

    /**
     * reconstructs a deadlone task from a stored string
     * @param n
     * @return the task with the details from the stored string
     */
    public static Task loadTask(String n) {
        String[] arr = n.split("\\|");
        boolean isDone = arr[0].equals("1");
        return new DeadlineTask(arr[1], isDone, arr[2]);
    }

    /**
     * constructs a string with the details of the task to be stored
     * @return string to be stored
     */
    @Override
    public String storeStr() {
        String status = isMarked() ? "1" : "0";
        return "D|" + status + "|" + getName() + "|" + deadline;
    }

    /**
     * toString() method
     * @return String format to be shown to users
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}