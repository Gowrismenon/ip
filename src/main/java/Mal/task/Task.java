package Mal.task;

/**
 * Represents a generic task that can be tracked.
 */
public abstract class Task {
    private boolean isDone;
    private String name;

    /**
     * Initialises an incomplete task with name
     * @param name
     */
    public Task(String name) {
        this(name, false);
    }

    /**
     * initialises a task with name and isDone
     * @param name
     * @param isDone
     */
    public Task(String name, boolean isDone) {
        assert name != null : "Task name cannot be null";
        assert !name.trim().isEmpty() : "Task name cannot be empty";
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Name of the task
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * checks if task is complete
     * @return boolean indicative of completion
     */
    public boolean isMarked() {
        return isDone;
    }

    /**
     * completes the task
     */
    public void finish() {
        this.isDone = true;
        assert this.isDone : "Failed to mark task as done";
    }
    /**
     * unfinishes the task
     */
    public void reOpen() {
        this.isDone = false;
        assert !this.isDone : "Failed to re-open task";
    }

    public abstract String storeStr();

    @Override
    public String toString() {
        String statusIcon = isDone ? "[X]" : "[ ]";
        return statusIcon + " " + this.name;
    }
}