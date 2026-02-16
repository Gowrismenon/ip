package Mal.logic;

import java.util.ArrayList;

import Mal.task.*;

/**
 * Manages the in-memory list of tasks.
 * Provides methods to add, delete, mark, and display tasks within the collection.
 */

public class TaskList {
    private static final String LINE = "_______________________________________________";
    private ArrayList<Task> list;

    /**
     * Initializes a TaskList with an existing collection of tasks.
     *
     * @param list An ArrayList of Task objects.
     */
    public TaskList(ArrayList<Task> list) {

        this.list = list;
    }

    /**
     * Adds a task to the list.
     *
     * @param t The task to be added.
     */
    public void add(Task t) {
        this.list.add(t);
    }

    /**
     * Prints all tasks currently in the list to the console.
     * If the list is empty, displays a prompt to add tasks.
     */
    public String list() {
        if (this.list.isEmpty()) {
            return "There is nothing to show, genius. Add some tasks!";
        }

        StringBuilder sb = new StringBuilder("Here's the master plan, i guess:\n");
        for (int i = 0; i < list.size(); i++) {
            sb.append((i + 1)).append(". ").append(list.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Marks a specific task as completed based on its index.
     * If the task is already marked, provides feedback to the user.
     *
     * @param idx The 0-based index of the task in the list.
     */
    public String mark(int idx) {
        if (list.get(idx).isMarked()) {
            return "Stop harping on the same old rotten apple!";
        } else {
            list.get(idx).finish();
            return "Alright..Now we're getting somewhere!\n" + list.get(idx).toString();
        }
    }

    /**
     * Reopens a specific task by marking it as not completed.
     *
     * @param idx The 0-based index of the task in the list.
     */
    public String unmark(int idx) {
        if (!list.get(idx).isMarked()) {
            return "You never got to this....";
        } else {
            list.get(idx).reOpen();
            return "Oh boohoo, we're reopening old wounds\n" + list.get(idx).toString();
        }
    }

    /**
     * Removes a task from the list based on its index.
     * Displays a confirmation message reflecting the task's completion status.
     *
     * @param idx The 0-based index of the task to be removed.
     */
    public String delete(int idx) {
        Task intermediate = list.get(idx);
        String response;
        if (intermediate.isMarked()) {
            response = "Right, that was inevitable\nDeleted: " + intermediate;
        } else {
            response = "Deleted:\n" + intermediate + "\nLet's call that a strategic decision, hm?";
        }
        list.remove(idx);
        return response;
    }
     /**
     * Displays a summary message after a task has been successfully added.
     * Includes the current task count for the user.
     */

     public String afterAdd() {
         int currIdx = list.size() - 1;
         Task curr = list.get(currIdx);

         return "Added:\n"
                 + curr.toString()
                 + "\nNow you have "
                 + list.size()
                 + " tasks for world domination";
     }

    /**
     * Returns the underlying ArrayList containing the tasks.
     * Primarily used for data persistence and storage.
     *
     * @return The internal ArrayList of Task objects.
     */
    public ArrayList<Task> get() {
        return this.list;
    }

    /**
     * finds and returns a filtered list of tasks with the keyword
     * @param name This is the keyword to search by
     */
    public String find(String name) {
        StringBuilder sb = new StringBuilder("Here is what you want:\n");
        ArrayList<Task> result = new ArrayList<>();
        String searchKey = name.toLowerCase();

        for (Task t : this.list) {
            if (t.getName().toLowerCase().contains(searchKey)) {
                result.add(t);
            }
        }

        if (result.isEmpty()) {
            return "Nothing found. My archives are incomplete.";
        } else {
            for (int i = 0; i < result.size(); i++) {
                sb.append((i + 1)).append(". ").append(result.get(i).toString()).append("\n");
            }
            return sb.toString();
        }
    }
}
