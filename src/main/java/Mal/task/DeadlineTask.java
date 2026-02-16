package Mal.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {
    private String deadline;

    public DeadlineTask(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    public DeadlineTask(String name, boolean isDone, String deadline) {
        super(name, isDone);
        this.deadline = deadline;
    }

    public static Task taskify(String s) {
        assert s != null : "Input to taskify cannot be null";
        String[] parts = s.split("/", 2);

        // Error handling for missing '/by'
        if (parts.length < 2) {
            System.out.println("Details. I need details. Magic has limits.");
            return new DeadlineTask(parts[0], "no deadline");
        }

        String rawDate = parts[1].split(" ", 2)[1];
        return new DeadlineTask(parts[0], formatIfDate(rawDate));
    }

    private static String formatIfDate(String dateStr) {
        try {
            LocalDate date = LocalDate.parse(dateStr);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            return dateStr;
        }
    }

    public static Task loadTask(String n) {
        String[] arr = n.split("\\|");
        boolean isDone = arr[0].equals("1");
        return new DeadlineTask(arr[1], isDone, arr[2]);
    }

    @Override
    public String storeStr() {
        String status = isMarked() ? "1" : "0";
        return "D|" + status + "|" + getName() + "|" + deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}