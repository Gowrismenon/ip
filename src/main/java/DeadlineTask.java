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
        try{
            if (interm.length <= 1) {
                throw new ArrayIndexOutOfBoundsException("Insufficient information");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Details. I need details. Magic has limits.");

        }
        return new DeadlineTask(details[0], interm[1]);
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
