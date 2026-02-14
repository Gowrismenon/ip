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
    public void list() {
        if (!this.list.isEmpty()) {
            System.out.println(LINE + "\nHere's the master plan, i guess");
            for (int i = 0; i < list.size(); i++) {
                int index = i + 1;
                System.out.println(index + ". " + list.get(i).toString());
            }
        } else {
            System.out.println("There is nothing to show, genius. Add some tasks!");
        }
    }

    /**
     * Marks a specific task as completed based on its index.
     * If the task is already marked, provides feedback to the user.
     *
     * @param idx The 0-based index of the task in the list.
     */
    public void mark(int idx) {
        if (list.get(idx).isMarked()) {
            System.out.println("Stop harping on the same old rotten apple!");
        } else {
            list.get(idx).finish();
            System.out.println("Alright..Now we're getting somewhere!\n" + list.get(idx).toString() + "\n"
                    + LINE);
        }

    }

    /**
     * Reopens a specific task by marking it as not completed.
     *
     * @param idx The 0-based index of the task in the list.
     */
    public void unmark(int idx) {
        if (!list.get(idx).isMarked()) {
            System.out.println("you never got to this...." + LINE);
        } else {
            list.get(idx).reOpen();
            System.out.println("Oh boohoo, we're reopening old wounds\n" + list.get(idx).toString()
                    + "\n" + LINE);
        }
    }

    /**
     * Removes a task from the list based on its index.
     * Displays a confirmation message reflecting the task's completion status.
     *
     * @param idx The 0-based index of the task to be removed.
     */
    public void delete(int idx) {
        Task intermediate = list.get(idx);
        if (intermediate.isMarked()) {
            System.out.print("Right, that was inevitable\nDeleted:"
                    + intermediate
                    + "\n"
                    + LINE
                    + "\n");
        } else {
            System.out.print("Deleted:\n"
                    + intermediate
                    + "\nLet's call that a strategic decision, hm?\n"
                    + LINE
                    + "\n");

        }
        list.remove(idx);

    }
     /**
     * Displays a summary message after a task has been successfully added.
     * Includes the current task count for the user.
     */

     public void afterAdd() {
         int currIdx = list.size() - 1;
         Task curr = list.get(currIdx);
         System.out.println(LINE
                 + "\nAdded:\n"
                 + curr.toString()
                 + "\nNow you have "
                 + list.size()
                 + " tasks for world domination\n"
                 + LINE);
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
    public void find(String name) {
        ArrayList<Task> result = new ArrayList<>();
        String searchKey = name.toLowerCase();

        for (Task t : this.list) {
            if (t.getName().toLowerCase().contains(searchKey)) {
                result.add(t);
            }
        }

        System.out.println(LINE + "\nHere is what you want:");

        if (result.isEmpty()) {
            System.out.println("Nothing found. My archives are incomplete.");
        } else {
            for (int i = 0; i < result.size(); i++) {
                int idx = i + 1;
                System.out.println(idx + ". " + result.get(i).toString());
            }
        }
        System.out.println(LINE);
    }
}
