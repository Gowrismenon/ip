package Mal.task;
/**
 * Represents a generic task that can be tracked.
 * This class serves as a base for specific task types like Todo, Deadline, and Event.
 */
public abstract class Task {
    private boolean isDone;
    private String name;

    /**
     * Initializes a new Task with the given name.
     * By default, the task is marked as not done.
     *
     * @param name The description of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
        assert name != null : "Task name cannot be null during initialization";
        assert !name.trim().isEmpty() : "Task name cannot be empty or only whitespace";
    }

    /**
     * Initializes a new Task with a given name and completion status.
     * Used primarily when loading tasks from storage.
     *
     * @param name The description of the task.
     * @param isDone The completion status of the task.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
        assert name != null : "Task name cannot be null during initialization";
        assert !name.trim().isEmpty() : "Task name cannot be empty or only whitespace";
    }

    /**
     * Returns the description of the task.
     *
     * @return The task name.
     */
    public String getName() {

        return this.name;
    }

    /**
     * Checks if the task has been marked as completed.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isMarked() {

        return isDone;
    }

    /**
     * Marks the task as completed.
     */
    public void finish() {

        this.isDone = true;
        assert this.isDone : "Failed to mark task as done";
    }

    /**
     * Returns a formatted string representation of the task for file storage.
     *
     * @return A string containing task details formatted for storage.
     */
    public abstract String storeStr();

    /**
     * Marks the task as not completed.
     */
    public void reOpen() {

        this.isDone = false;
        assert !this.isDone : "Failed to re-open task";
    }

    /**
     * Returns a string representation of the task, including its status icon.
     *
     * @return A formatted string showing task status and description.
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }

}
