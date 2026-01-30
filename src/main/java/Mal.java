import java.util.Scanner;

public class Mal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String logo = "Mal";
        String line = "_______________________________________________";
        String byeMsg = "Right, I'm out then, Stay rotten to the core!";
        System.out.println(line +
                            "\nHello I'm " + logo +
                            "\nYou summoned me?\n" +
                            line);
        while(true) {
            String input = sc.nextLine();
            if(input.equals("bye")) {
                System.out.println(line + "\n" + byeMsg + "\n" + line);
                break;
            }
            System.out.println(line + "\n" + input + "\n" + line);
        }
        sc.close();
    }
}
