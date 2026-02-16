package Mal.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 */

public class EventTask extends Task {
    private String start;
    private String end;

    /**
     * Initializes a new EventTask with a name, start time, and end time.
     *
     * @param name Description of the event.
     * @param start The start time or date of the event.
     * @param end The end time or date of the event.
     */
    public EventTask(String name, String start, String end) {
        super(name);
        assert name != null : "Task name cannot be null";
        assert start != null : "Start time cannot be null";
        assert end != null : "End time cannot be null";
        this.start = start;
        this.end = end;
    }

    /**
     * Initializes a new EventTask with a name, completion status, start time, and end time.
     * Primarily used for loading tasks from storage.
     *
     * @param name Description of the event.
     * @param isDone The completion status of the task.
     * @param start The start time or date of the event.
     * @param end The end time or date of the event.
     */
    public EventTask(String name, boolean isDone, String start, String end) {
        super(name, isDone);
        assert name != null : "Task name cannot be null";
        assert start != null : "Start time cannot be null";
        assert end != null : "End time cannot be null";
        this.start = start;
        this.end = end;
    }

    /**
     * Parses user input to create an EventTask.
     * Handles date parsing if the input matches ISO format (YYYY-MM-DD).
     *
     * @param s The raw command string containing task details and time delimiters.
     * @return A new EventTask object.
     */
    public static Task taskify(String s) {
        assert s != null : "Input string to taskify cannot be null";
        String[] details = s.split("/");

        if (details.length < 3) {
            System.out.println("Right, we have something to do....but we dom't know what and when?");
        }
        String[] interm = details[1].split(" ", 2);
        String[] interm2 = details[2].split(" ", 2);

        try {
            if (interm.length <= 1 || interm2.length <= 1) {
                throw new ArrayIndexOutOfBoundsException("Insufficient information");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("I have magic, not mind reading abilities. I need details");

        }

        //handle data
        String start = interm[1];
        String end = interm2[1];
        boolean isSDate = true;
        boolean isEDate = true;
        String st = "";
        String en = "";
        try {
            LocalDate d = LocalDate.parse(start.trim());
            st = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            isSDate = false;
        }
        try {
            LocalDate d1 = LocalDate.parse(end.trim());
            en = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            isEDate = false;
        }
        if (isSDate) {
            start = st;
        }
        if (isEDate) {
            end = en;
        }
        Task t = new EventTask(details[0], start, end);
        return t;
    }

    /**
     * Reconstructs an EventTask from a stored data string.
     *
     * @param n The pipe-delimited string from the storage file.
     * @return An EventTask with the saved state and time details.
     */
    public static Task loadTask(String n) {
        assert n != null : "Storage string to loadTask cannot be null";
        String[] arr = n.split("\\|");
        if (arr[0].equals("1")) {
            return new EventTask(arr[1], true, arr[2], arr[3]);
        } else {
            return new EventTask(arr[1], false, arr[2], arr[3]);
        }

    }

    /**
     * Returns a formatted string for saving the event to a file.
     *
     * @return A string in the format E|status|name|start|end.
     */
    @Override
    public String storeStr() {
        assert this.getName() != null : "Task name is null during save";
        assert this.start != null : "Start time is null during save";
        assert this.end != null : "End time is null during save";
        if (this.isMarked()) {
            return "E|1|" + this.getName() + "|" + this.start + "|" + this.end;
        } else {
            return "E|0|" + this.getName() + "|" + this.start + "|" + this.end;
        }

    }

    /**
     * Returns a string representation of the event for display.
     *
     * @return Formatted string with [E] prefix and time range.
     */
    @Override
    public String toString() {

        return "[E]" + super.toString() + "(from: " + this.start + " to: " + this.end + ")";
    }

}
