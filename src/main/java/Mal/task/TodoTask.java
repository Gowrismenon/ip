package Mal.task;

public class TodoTask extends Task {
    public TodoTask(String name) {
        super(name);
    }

    public TodoTask(String name, boolean isDone) {
        super(name, isDone);
    }

    public static Task taskify(String s) {
        assert s != null : "taskify received null string";
        return new TodoTask(s);
    }

    public static Task loadTask(String n) {
        assert n != null : "loadTask received null storage string";
        String[] arr = n.split("\\|");
        boolean isDone = arr[0].equals("1");
        return new TodoTask(arr[1], isDone);
    }

    @Override
    public String storeStr() {
        String status = isMarked() ? "1" : "0";
        return "T|" + status + "|" + getName();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}