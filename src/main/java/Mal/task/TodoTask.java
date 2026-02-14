package Mal.task;

/**
 * Deals with all tasks of the 'todo' type
 */

public class TodoTask extends Task {
    /**
     * Initializes a new TodoTask with the specified name
     * @param name String
     */
    public TodoTask(String name) {
        super(name);
    }

    /**
     * constructor using name and to set 'marked' state
     * @param name String name of the task
     * @param isDone boolean to determine 'marked' state
     */
    public TodoTask(String name, boolean isDone) {
        super(name, isDone);

    }

    /**
     * Makes a task out of a string of details
     * @param s String that contains details of the task
     * @return Task This is the task with the details of the Sting
     */
    public static Task taskify(String s) {
        return new TodoTask(s);
    }

    /**
     * Reconstructs a TodoTask from a stored data string.
     * @param n String from the stored files
     * @return Task with the details
     */
    public static Task loadTask(String n) {
        String[] arr = n.split("\\|");
        if (arr[0].equals("1")) {
            return new TodoTask(arr[1], true);
        } else {
            return new TodoTask(arr[1], false);
        }
    }

    /**
     * Converts a Task to a String in the format to be stored
     * @return String This is how the task will be stored
     */
    @Override
    public String storeStr() {
        if (this.isMarked()) {
            return "T|1|" + this.getName();
        } else {
            return "T|0|" + this.getName();
        }

    }

    /**
     * Converts details of the task to be printed out for the user
     * @return String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
