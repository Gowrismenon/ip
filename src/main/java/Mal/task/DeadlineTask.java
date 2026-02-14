package Mal.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task that needs to be completed by a specific deadline.
 */
public class DeadlineTask extends Task {
    private String deadline;

    /**
     * Initializes a new DeadlineTask with a name and a deadline.
     *
     * @param name Description of the task.
     * @param deadline The time or date by which the task must be completed.
     */
    public DeadlineTask(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    /**
     * Initializes a new DeadlineTask with a name, completion status, and deadline.
     * Primarily used for loading tasks from storage.
     *
     * @param name Description of the task.
     * @param isDone The completion status of the task.
     * @param deadline The time or date by which the task must be completed.
     */
    public DeadlineTask(String name, boolean isDone, String deadline) {
        super(name, isDone);
        this.deadline = deadline;
    }


    /**
     * Parses user input to create a DeadlineTask.
     * Attempts to format the deadline if provided in ISO date format.
     *
     * @param s The raw command string containing task details and the deadline delimiter.
     * @return A new DeadlineTask object.
     */
    public static Task taskify(String s) {
        String[] details = s.split("/");
        String[] interm = details[1].split(" ", 2);
        //handle inadequate details
        try {
            if (interm.length <= 1) {
                throw new ArrayIndexOutOfBoundsException("Insufficient information");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Details. I need details. Magic has limits.");

        }

        //handle date input
        String deadline = interm[1];
        boolean isDate = true;
        String dead = "";
        try {
            LocalDate d = LocalDate.parse(deadline);
            dead = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            isDate = false;
        }
        if (isDate) {
            deadline = dead;
        }
        return new DeadlineTask(details[0], deadline);

    }

    /**
     * Reconstructs a DeadlineTask from a stored data string.
     *
     * @param n The pipe-delimited string from the storage file.
     * @return A DeadlineTask with the saved state and deadline.
     */
    public static Task loadTask(String n) {
        String[] arr = n.split("\\|");
        if (arr[0].equals("1")) {
            return new DeadlineTask(arr[1], true, arr[2]);
        } else {
            return new DeadlineTask(arr[1], false, arr[2]);
        }
    }

    /**
     * Returns a formatted string for saving the deadline task to a file.
     *
     * @return A string in the format D|status|name|deadline.
     */
    @Override
    public String storeStr() {
        if (this.isMarked()) {
            return "D|1|" + this.getName() + "|" + this.deadline;
        } else {
            return "D|0|" + this.getName() + "|" + this.deadline;
        }

    }

    /**
     * Returns a string representation of the deadline task for display.
     *
     * @return Formatted string with [D] prefix and the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";

    }



}
