package Mal.logic;

import java.util.ArrayList;
import Mal.task.Task;

/**
 * Manages the in-memory list of tasks.
 * Provides methods to add, delete, mark, and display tasks within the collection.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Initializes a TaskList with an existing collection of tasks.
     *
     * @param list An ArrayList of Task objects.
     */
    public TaskList(ArrayList<Task> list) {
        assert list != null : "TaskList cannot be initialized with a null list";
        this.list = list;
    }

    public void add(Task t) {
        assert t != null : "Cannot add a null task to the list";
        this.list.add(t);
    }

    /**
     * Returns all tasks currently in the list as a formatted string.
     */
    public String list() {
        if (this.list.isEmpty()) {
            return "There is nothing to show, genius. Add some tasks!";
        }

        StringBuilder sb = new StringBuilder("Here's the master plan, i guess:\n");
        return buildTaskString(sb, this.list);
    }

    public String mark(int idx) {
        validateIndex(idx);
        Task task = list.get(idx);

        // Guard clause: handle the "unhappy" state first
        if (task.isMarked()) {
            return "Stop harping on the same old rotten apple!";
        }

        task.finish();
        return "Alright..Now we're getting somewhere!\n" + task.toString();
    }

    public String unmark(int idx) {
        validateIndex(idx);
        Task task = list.get(idx);

        // Guard clause: handle the "unhappy" state first
        if (!task.isMarked()) {
            return "You never got to this....";
        }

        task.reOpen();
        return "Oh boohoo, we're reopening old wounds\n" + task.toString();
    }

    public String delete(int idx) {
        validateIndex(idx);
        Task taskToDelete = list.get(idx);

        String response = getDeleteMessage(taskToDelete);
        list.remove(idx);

        return response;
    }

    public String afterAdd() {
        assert !list.isEmpty() : "afterAdd called on an empty list";
        Task lastTask = list.get(list.size() - 1);

        return "Added:\n"
                + lastTask.toString()
                + "\nNow you have "
                + list.size()
                + " tasks for world domination";
    }

    public ArrayList<Task> get() {
        return this.list;
    }

    public String find(String name) {
        assert name != null : "Search keyword cannot be null";
        ArrayList<Task> filteredResults = filterTasksByName(name);

        if (filteredResults.isEmpty()) {
            return "Nothing found. My archives are incomplete.";
        }

        StringBuilder sb = new StringBuilder("Here is what you want:\n");
        return buildTaskString(sb, filteredResults);
    }

    // --- Private Helper Methods (SLAP Improvements) ---

    private void validateIndex(int idx) {
        assert idx >= 0 && idx < list.size() : "Index out of bounds: " + idx;
    }

    private String getDeleteMessage(Task task) {
        if (task.isMarked()) {
            return "Right, that was inevitable\nDeleted: " + task;
        }
        return "Deleted:\n" + task + "\nLet's call that a strategic decision, hm?";
    }

    private ArrayList<Task> filterTasksByName(String name) {
        ArrayList<Task> results = new ArrayList<>();
        String searchKey = name.toLowerCase();

        for (Task t : this.list) {
            if (t.getName().toLowerCase().contains(searchKey)) {
                results.add(t);
            }
        }
        return results;
    }

    private String buildTaskString(StringBuilder sb, ArrayList<Task> tasksToFormat) {
        for (int i = 0; i < tasksToFormat.size(); i++) {
            sb.append((i + 1)).append(". ").append(tasksToFormat.get(i).toString()).append("\n");
        }
        return sb.toString();
    }
}