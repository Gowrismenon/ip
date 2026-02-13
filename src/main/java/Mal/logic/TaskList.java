package Mal.logic;

import java.util.ArrayList;

import Mal.task.*;


public class TaskList {
    private ArrayList<Task> list;
    private static final String LINE = "_______________________________________________";

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void add(Task t) {
        this.list.add(t);
    }

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

    public void mark(int idx) {
        if(list.get(idx).isMarked()) {
            System.out.println("Stop harping on the same old rotten apple!");
        } else {
            list.get(idx).finish();
            System.out.println("Alright..Now we're getting somewhere!\n" + list.get(idx).toString() +"\n"
                    + LINE);
        }

    }

    public void unmark(int idx) {
        if(!list.get(idx).isMarked()) {
            System.out.println("you never got to this...." + LINE);
        } else {
            list.get(idx).reOpen();
            System.out.println("Oh boohoo, we're reopening old wounds\n" + list.get(idx).toString()
                    + "\n" + LINE);
        }
    }

    public void delete(int idx) {
        Task intermediate = list.get(idx);
        if(intermediate.isMarked()) {
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
                    + "\n" );

        }
        list.remove(idx);

        }

        public void afterAdd() {
            int currIdx = list.size() - 1;
            Task curr = list.get(currIdx);
            System.out.println(LINE +
                    "\nAdded:\n" +
                    curr.toString() +
                    "\nNow you have " +
                    list.size() +
                    " tasks for world domination\n" +
                   LINE);
    }

    public ArrayList<Task> get() {
        return this.list;
    }
}
