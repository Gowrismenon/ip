package Mal.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that occurs over a specific time frame.
 */
public class EventTask extends Task {
    private String start;
    private String end;

    public EventTask(String name, String start, String end) {
        this(name, false, start, end);
    }

    public EventTask(String name, boolean isDone, String start, String end) {
        super(name, isDone);
        assert start != null : "Start time cannot be null";
        assert end != null : "End time cannot be null";
        this.start = start;
        this.end = end;
    }

    public static Task taskify(String s) {
        assert s != null : "Input string to taskify cannot be null";
        String[] details = s.split("/");

        // Guard clause for missing delimiters
        if (details.length < 3) {
            System.out.println("Right, we have something to do....but we don't know what and when?");
            return new EventTask(details[0], "TBD", "TBD");
        }

        String rawStart = details[1].split(" ", 2)[1];
        String rawEnd = details[2].split(" ", 2)[1];

        return new EventTask(details[0], formatIfDate(rawStart), formatIfDate(rawEnd));
    }

    private static String formatIfDate(String dateStr) {
        try {
            LocalDate d = LocalDate.parse(dateStr.trim());
            return d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            return dateStr;
        }
    }

    public static Task loadTask(String n) {
        assert n != null : "Storage string to loadTask cannot be null";
        String[] arr = n.split("\\|");
        boolean isDone = arr[0].equals("1");
        return new EventTask(arr[1], isDone, arr[2], arr[3]);
    }

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