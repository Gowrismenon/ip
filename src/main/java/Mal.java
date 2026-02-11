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
        Storage storage = new Storage(PATH);

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
        TaskList TL = new TaskList(storage.load());


        //input
        while(true) {
            String input = sc.nextLine();
            String[] arr = input.split(" ", 2);
            String taskType = arr[0];

            //handle bye
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(line + "\n" + byeMsg + "\n" + line);
                //write to file
                storage.save(list);
                break;
            }

            //handle list
            if (input.equalsIgnoreCase("list")) {
                TL.list();
                System.out.println(line);

                //marking
            } else if(arr[0].equalsIgnoreCase("mark")) {//marking and unmarking
                try{
                    TL.mark(arr);
                } catch (NumberFormatException e) {
                    System.out.println("I don't know all your tasks by name, give me a number!\n" + line);
                    continue;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("I'm sorry, task number WHAT?!?!?\n" + line);
                    continue;
                }
            //unmark
            } else if(arr[0].equalsIgnoreCase("unmark")) {
                try {
                    TL.unmark(arr);
                } catch (NumberFormatException e) {
                    System.out.println("I don't know all your tasks by name, give me a number!\n" + line );
                    continue;
                }


            } else if(arr[0].equalsIgnoreCase("delete")) {
                if(arr.length == 1) {
                    System.out.println("Delete what exactly?");
                    continue;
                } else {
                    TL.delete(arr);
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
