package Mal.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import Mal.logic.MalException;

/**
 * Represents a task that occurs over a specific time frame.
 */
public class EventTask extends Task {
    private String start;
    private String end;

    /**
     * Initialises an event task with a name and start,end time
     * @param name
     * @param start
     * @param end
     */
    public EventTask(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    /**
     * Initialises an event task with a name and start,end time, but with variable isDone state(set by user)
     * @param name
     * @param isDone
     * @param start
     * @param end
     */
    public EventTask(String name, boolean isDone, String start, String end) {
        super(name, isDone);
        assert start != null : "Start time cannot be null";
        assert end != null : "End time cannot be null";
        this.start = start;
        this.end = end;
    }

    /**
     * takes in input details and returns an event with those details
     * @param s
     * @return event task
     */
    public static Task taskify(String s) {
        assert s != null : "Input string to taskify cannot be null";
        String[] details = s.split("/");


        if (details.length < 3) {
            throw new MalException("insufficient details");
        }

        String rawStart = details[1].split(" ", 2)[1];
        String rawEnd = details[2].split(" ", 2)[1];

        return new EventTask(details[0], formatIfDate(rawStart), formatIfDate(rawEnd));
    }

    /**
     * formats start and end as a date
     * @param dateStr
     * @return string formatted as a date
     */
    private static String formatIfDate(String dateStr) {
        try {
            LocalDate d = LocalDate.parse(dateStr.trim());
            return d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            return dateStr;
        }
    }
    /**
     * reconstructs event task from a stored string
     * @param n
     * @return the task with the details from the stored string
     */
    public static Task loadTask(String n) {
        assert n != null : "Storage string to loadTask cannot be null";
        String[] arr = n.split("\\|");
        boolean isDone = arr[0].equals("1");
        return new EventTask(arr[1], isDone, arr[2], arr[3]);
    }
    /**
     * constructs a string with the details of the task to be stored
     * @return string to be stored
     */
    @Override
    public String storeStr() {
        String status = isMarked() ? "1" : "0";
        return "E|" + status + "|" + this.getName() + "|" + this.start + "|" + this.end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.start + " to: " + this.end + ")";
    }
}