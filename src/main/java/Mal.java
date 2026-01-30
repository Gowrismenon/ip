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
            String[] arr = input.split(" ");

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
                Task curr = new Task(input);
                list.add(curr);
                System.out.println(line + "\nadded: " + input + "\n" + line);
            }

        }
        sc.close();
    }
}
