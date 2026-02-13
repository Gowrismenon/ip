package Mal.task;

public class TodoTask extends Task {

    public TodoTask(String name) {
        super(name);
    }

    public TodoTask(String name, boolean isDone) {
        super(name, isDone);

    }


    public static Task taskify(String s) {
        return new TodoTask(s);
    }

    public static Task loadTask(String n) {
        String[] arr = n.split("\\|");
        if(arr[0].equals("1")) {
            return new TodoTask( arr[1], true);
        } else {
            return new TodoTask( arr[1], false);
        }
    }

    @Override
    public String storeStr() {
        if(this.isMarked()) {
            return "T|1|" + this.getName();
        } else {
            return "T|0|" + this.getName();
        }

    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
