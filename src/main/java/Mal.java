import java.util.Scanner;
import java.util.ArrayList;


public class Mal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Task> list = new ArrayList<>();

        String logo = "Mal";
        String line = "_______________________________________________";
        String byeMsg = "Finally. I thought you'd never stop talking. Stay rotten.";

        //greeting
        System.out.println(line +
                            "\nHello I'm " + logo +
                            "\nYou summoned me?\n" +
                            line);

        //input
        while(true) {
            String input = sc.nextLine();
            String[] arr = input.split(" ", 2);
            String taskType = arr[0];

            //handle bye
            if (input.equals("bye")) {
                System.out.println(line + "\n" + byeMsg + "\n" + line);
                break;
            }

            //handle list
            if (input.equals("list")) {
                if (!list.isEmpty()) {
                    System.out.println(line + "\nHere's the master plan, i guess");
                    for (int i = 0; i < list.size(); i++) {
                        int index = i + 1;
                        System.out.println(index + ". " + list.get(i).toString() + "\n");
                    }
                } else {
                    System.out.println("ummm... there is no chaos yet");
                }
                System.out.println(line);

            } else if(arr[0].equals("mark")) {//marking and unmarking
                int idx = Integer.parseInt(arr[1]) - 1;
                list.get(idx).finish();
                System.out.println("Alright..Now we're getting somewhere!\n" + list.get(idx).toString());

            } else if(arr[0].equals("unmark")) {
                int idx = Integer.parseInt(arr[1]) - 1;
                list.get(idx).reOpen();
                System.out.println("Oh boohoo, we're reopening old wounds\n" + list.get(idx).toString());

            } else { // handle input
                Task curr;

                if (taskType.equals("todo")) {
                    curr = new TodoTask(arr[1]);
                } else if (taskType.equals("deadline")) {
                    String[] details = arr[1].split("/");
                    curr = new DeadlineTask(details[0], details[1].split(" ",2)[1]);

                } else {
                    String[] details = arr[1].split("/");
                    curr = new EventTask(details[0], details[1].split(" ",2)[1], details[2].split(" ",2)[1]);
                }
                list.add(curr);
                System.out.println(line +
                                    "\nAdded:\n" +
                                    curr.toString() +
                                    "\nNow you have " +
                                    list.size() +
                                    " tasks for world domination\n" +
                                    line);
            }

        }
        sc.close();
    }
}
