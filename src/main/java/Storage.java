import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    //load from file
    public ArrayList<Task> load() {
        File file = new File(this.filePath);
        ArrayList<Task> list = new ArrayList<>();

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
        return list;
    }

    //save
    public void save(ArrayList<Task> list) {
        try (FileWriter writer = new FileWriter(this.filePath)) {
            for(Task t: list) {
                writer.write(t.storeStr());
                writer.write(System.lineSeparator());
            }

        } catch (IOException e) {
            System.out.println("slow your roll! Give me time to communicate the plan");
        }
    }
}
