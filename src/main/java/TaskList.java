import java.util.AbstractList;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;
    private static final String LINE = "_______________________________________________";

    public TaskList(ArrayList<Task> list) {
        this.list = list;
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

    public void mark(String[] arr) {
        int idx = -1;
        idx = Integer.parseInt(arr[1]) - 1;

        if (0 <= idx && idx < list.size()) {
            if(list.get(idx).isMarked()) {
                System.out.println("Stop harping on the same old rotten apple!");
            } else {
                list.get(idx).finish();
                System.out.println("Alright..Now we're getting somewhere!\n" + list.get(idx).toString() +"\n"
                        + LINE);
            }
        } else {
            System.out.println("Maybe you should finish one of the million tasks you have piled on first.");
        }
    }

    public void unmark(String[] arr) {
        int idx = Integer.parseInt(arr[1]) - 1;;

        if (0 <= idx && idx < list.size()) {
            if(!list.get(idx).isMarked()) {
                System.out.println("you never got to this...." + LINE);
            } else {
                list.get(idx).reOpen();
                System.out.println("Oh boohoo, we're reopening old wounds\n" + list.get(idx).toString()
                        + "\n" + LINE);
            }
        } else {
            System.out.println("Ah yes, task number 'that one'. A classic. Tragically fictional\n" + LINE);
        }
    }
}
