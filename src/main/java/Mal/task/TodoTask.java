package Mal.task;

public class TodoTask extends Task {
    /**
     * initialises a todo task with name
     */
    public TodoTask(String name) {
        super(name);
    }
    /**
     * initialises a todo task with name and isDone state
     */
    public TodoTask(String name, boolean isDone) {

        super(name, isDone);
    }

    /**
     * takes in input details and returns an event with those details
     * @param s
     * @return event task
     */
    public static Task taskify(String s) {
        assert s != null : "taskify received null string";
        return new TodoTask(s);
    }

    /**
     * reconstructs event task from a stored string
     * @param n
     * @return the task with the details from the stored string
     */
    public static Task loadTask(String n) {
        assert n != null : "loadTask received null storage string";
        String[] arr = n.split("\\|");
        boolean isDone = arr[0].equals("1");
        return new TodoTask(arr[1], isDone);
    }

    /**
     * constructs a string with the details of the task to be stored
     * @return string to be stored
     */
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