import java.util.Scanner;
import java.util.ArrayList;


public class Mal {

    public static void main(String[] args) throws MalException{
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
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(line + "\n" + byeMsg + "\n" + line);
                break;
            }

            //handle list
            if (input.equalsIgnoreCase("list")) {
                if (!list.isEmpty()) {
                    System.out.println(line + "\nHere's the master plan, i guess");
                    for (int i = 0; i < list.size(); i++) {
                        int index = i + 1;
                        System.out.println(index + ". " + list.get(i).toString());
                    }
                } else {
                    System.out.println("There is nothing to show, genius. Add some tasks!");
                }

                System.out.println(line);
                //marking
            } else if(arr[0].equalsIgnoreCase("mark")) {//marking and unmarking
                int idx = Integer.parseInt(arr[1]) - 1;
                if (0 <= idx && idx < list.size()) {
                    if(list.get(idx).isMarked()) {
                        System.out.println("Stop harping on the same old rotten apple!");
                    } else {
                        list.get(idx).finish();
                        System.out.println("Alright..Now we're getting somewhere!\n" + list.get(idx).toString());
                    }
                } else {
                    System.out.println("Maybe you should finish one of the million tasks you have piled on first.");
                }

            } else if(arr[0].equalsIgnoreCase("unmark")) {
                int idx = Integer.parseInt(arr[1]) - 1;
                list.get(idx).reOpen();
                System.out.println("Oh boohoo, we're reopening old wounds\n" + list.get(idx).toString());

            } else { // handle input
                Task curr;
                try {
                    if (arr.length <= 1) {
                        throw new ArrayIndexOutOfBoundsException("Insufficient information");

                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("I have magic, not mind reading abilities. I need details");
                    continue;
                }

                if (taskType.equalsIgnoreCase("todo")) {
                    curr = new TodoTask(arr[1]);
                } else if (taskType.equalsIgnoreCase("deadline")) {
                    String[] details = arr[1].split("/");
                    String[] interm = details[1].split(" ",2);
                    try{
                        if (interm.length <= 1) {
                        throw new ArrayIndexOutOfBoundsException("Insufficient information");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("I have magic, not mind reading abilities. I need details");
                    continue;
                    }
                    curr = new DeadlineTask(details[0], interm[1]);

                } else {
                    String[] details = arr[1].split("/");
                    String[] interm = details[1].split(" ",2);
                    String[] interm2 = details[2].split(" ",2);
                    curr = new EventTask(details[0], interm[1], interm2[1]);
                    try {
                        if (interm.length <= 1 || interm2.length <= 1) {
                            throw new ArrayIndexOutOfBoundsException("Insufficient information");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("I have magic, not mind reading abilities. I need details");
                        continue;
                    }

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
