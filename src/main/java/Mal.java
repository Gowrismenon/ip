import java.util.Scanner;
import java.util.ArrayList;

public class Mal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> list = new ArrayList<>();

        String logo = "Mal";
        String line = "_______________________________________________";
        String byeMsg = "Finally. I thought you'd never stop talking. Stay rotten.";
        System.out.println(line +
                            "\nHello I'm " + logo +
                            "\nYou summoned me?\n" +
                            line);
        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(line + "\n" + byeMsg + "\n" + line);
                break;
            }
            if (input.equals("list")) {
                if (!list.isEmpty()) {
                    System.out.println(line + "\nHere's the master plan, i guess");
                    for (int i = 0; i < list.size(); i++) {
                        int index = i + 1;
                        System.out.println(index + ". " + list.get(i) + "\n");
                    }
                } else {
                    System.out.println("ummm... there is no chaos yet");
                }
                System.out.println(line);
            } else {
                list.add(input);
                System.out.println(line + "\nadded: " + input + "\n" + line);
            }
        }
        sc.close();
    }
}
