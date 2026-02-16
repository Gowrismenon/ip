package Mal.task;

/**
 * Represents a generic task that can be tracked.
 */
public abstract class Task {
    private boolean isDone;
    private String name;

    public Task(String name) {
        this(name, false);
    }

    public Task(String name, boolean isDone) {
        assert name != null : "Task name cannot be null";
        assert !name.trim().isEmpty() : "Task name cannot be empty";
        this.name = name;
        this.isDone = isDone;
    }

    public String getName() {
        return this.name;
    }

    public boolean isMarked() {
        return isDone;
    }

    public void finish() {
        this.isDone = true;
        assert this.isDone : "Failed to mark task as done";
    }

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