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
        String[] details = s.split("/");
        String[] interm = details[1].split(" ",2);
        //handle inadequate details
        try{
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
        try{
            LocalDate d = LocalDate.parse(deadline);
            dead = d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch(DateTimeParseException e) {
            isDate = false;
        }
        if(isDate) {
            deadline = dead;
        }
        return new DeadlineTask(details[0], deadline);

    }

    public static Task loadTask(String n) {
        String[] arr = n.split("\\|");
        if(arr[0].equals("1")) {
            return new DeadlineTask( arr[1], true, arr[2]);
        } else {
            return new DeadlineTask( arr[1], false, arr[2]);
        }
    }

    @Override
    public String storeStr() {
        if(this.isMarked()) {
            return "D|1|" + this.getName() + "|" + this.deadline;
        } else {
            return "D|0|" + this.getName() + "|" + this.deadline;
        }

    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";

    }



}
