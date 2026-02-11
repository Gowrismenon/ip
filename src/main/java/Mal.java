import javax.xml.crypto.Data;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//basic file stuff done, now need to write the opnening logic,


public class Mal {
    private static final String PATH = "./Data/Mal.txt" ;
    public static void main(String[] args) throws MalException{
        Scanner sc = new Scanner(System.in);
        File file = new File(PATH);

        ArrayList<Task> list = new ArrayList<>();
        ArrayList<String> commands = new ArrayList<>(Arrays.asList("list",
                                                                    "mark <task no.>",
                                                                    "unmark <task no.>",
                                                                    "delete <task no.>,",
                                                                    "todo <taskname>",
                                                                    "deadline <taskname> / by <deadline>",
                                                                    "event <taskname> /from <start> /to <end>",
                                                                    "bye"));


        String logo = "Mal";
        String line = "_______________________________________________";
        String byeMsg = "Finally. I thought you'd never stop talking. Stay rotten.";

        //greeting
        System.out.println(line +
                            "\nHello I'm " + logo +
                            "\nYou summoned me?\n" +
                            line);
        if(!file.exists() || file.length() == 0) {
            System.out.println("Seems like you're starting from scratch!");
        } else {
            try (Scanner scan = new Scanner(file)) {
                while(scan.hasNextLine()) {
                    String s = scan.nextLine().trim();
                    if(s.isEmpty()) continue; // skip empty lines
                    String[] det = s.split("\\|", 2);
                    if(det.length < 2) continue; // skip malformed lines

                    Task forNow;
                    if(det[0].equalsIgnoreCase("T")) {
                        forNow = TodoTask.loadTask(det[1]);
                    } else if(det[0].equalsIgnoreCase("D")) {
                        forNow = DeadlineTask.loadTask(det[1]);
                    } else {
                        forNow = EventTask.loadTask(det[1]);
                    }
                    list.add(forNow);
                }
            } catch (FileNotFoundException e) {
                System.out.println("You gotta give me something to work off of, " +
                        "I cannot show you a file that doesn't exist!");
            }
        }

        //input
        while(true) {
            String input = sc.nextLine();
            String[] arr = input.split(" ", 2);
            String taskType = arr[0];

            //handle bye
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(line + "\n" + byeMsg + "\n" + line);
                //write to file
                try (FileWriter writer = new FileWriter(PATH)) {
                    for(Task t: list) {
                        writer.write(t.storeStr());
                        writer.write(System.lineSeparator());
                    }

                } catch (IOException e) {
                    System.out.println("slow your roll! Give me time to communicate the plan");
                }
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
                int idx = -1;
                try {
                    idx = Integer.parseInt(arr[1]) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("I don't know all your tasks by name, give me a number!");
                    continue;
                }

                if (0 <= idx && idx < list.size()) {
                    if(list.get(idx).isMarked()) {
                        System.out.println("Stop harping on the same old rotten apple!");
                    } else {
                        list.get(idx).finish();
                        System.out.println("Alright..Now we're getting somewhere!\n" + list.get(idx).toString() +"\n"
                                            + line);
                    }
                } else {
                    System.out.println("Maybe you should finish one of the million tasks you have piled on first.");
                }
            //unmark
            } else if(arr[0].equalsIgnoreCase("unmark")) {
                int idx = -1;
                try {
                    idx = Integer.parseInt(arr[1]) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("I don't know all your tasks by name, give me a number!");
                    continue;
                }
                if (0 <= idx && idx < list.size()) {
                    if(!list.get(idx).isMarked()) {
                        System.out.println("you never got to this...." + line);
                    } else {
                        list.get(idx).reOpen();
                        System.out.println("Oh boohoo, we're reopening old wounds\n" + list.get(idx).toString()
                                            + "\n" + line);
                    }
                } else {
                    System.out.println("Ah yes, task number 'that one'. A classic. Tragically fictional");
                }


            } else if(arr[0].equalsIgnoreCase("delete")) {
                int idx = Integer.parseInt(arr[1]) - 1;
                if(idx < 0 || idx >= list.size()) {
                    System.out.println("You can't delete what was never added");
                } else {
                    Task intermediate = list.get(idx);
                    if(intermediate.isMarked()) {
                        System.out.print("Right, that was inevitable\nDeleted:"
                                        + intermediate
                                        + "\n"
                                        + line);
                    } else {
                        System.out.print("Deleted:\n"
                                + intermediate
                                + "\nLet's call that a strategic decision, hm?\n"
                                + line);

                    }
                    list.remove(idx);

                }
            }
            else { // handle input
                String currS;
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
                    curr = TodoTask.taskify(arr[1]);
                    currS = curr.toString();
                } else if (taskType.equalsIgnoreCase("deadline")) {
                    curr = DeadlineTask.taskify(arr[1]);
                    currS = curr.toString();

                } else if (arr[0].equalsIgnoreCase("Event")){
                    curr = EventTask.taskify(arr[1]);
                    currS = curr.toString();

                } else {
                    System.out.println("Oops you messed up! Let me help" +
                                        "\nPerhaps you meant:");
                    for(String elem: commands) {
                        System.out.println(elem);
                    }
                    continue;
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
