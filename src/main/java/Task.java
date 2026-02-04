public class Task {
    private boolean isDone;
    private String name;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public boolean isMarked() {
        return isDone;
    }

    public void finish() {
        this.isDone = true;
    }

    public void reOpen() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        if(isDone) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }

}
