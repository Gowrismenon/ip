package Mal.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends Task {
    private String start;
    private String end;

    public EventTask(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    public EventTask(String name, boolean isDone, String start, String end) {
        super(name,isDone);
        this.start = start;
        this.end = end;
    }

    public static Task taskify(String s) {
        String[] details = s.split("/");
        String[] interm = details[1].split(" ",2);
        String[] interm2 = details[2].split(" ",2);

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
        try{
            LocalDate d = LocalDate.parse(start.trim());
            st = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch(DateTimeParseException e) {
            isSDate = false;
        } try {
            LocalDate d1 = LocalDate.parse(end);
            en = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException e) {
            isEDate = false;
        }
        if(isSDate) {
            start = st;
        }
        if(isEDate) {
            end = en;
        }
        Task t = new EventTask(details[0], start, end);
        return t;
    }

    public static Task loadTask(String n) {
        String[] arr = n.split("\\|");
        if(arr[0].equals("1")) {
            return new EventTask( arr[1], true, arr[2], arr[3]);
        } else {
            return new EventTask( arr[1], false, arr[2], arr[3]);
        }

    }

    @Override
    public String storeStr() {
        if(this.isMarked()) {
            return "E|1|" + this.getName() + "|" + this.start + "|" + this.end;
        } else {
            return "E|0|" + this.getName() + "|" + this.start + "|" + this.end;
        }

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.start + " to: " + this.end + ")";
    }

}
