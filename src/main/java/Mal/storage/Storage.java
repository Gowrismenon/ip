package Mal.storage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

import Mal.task.*;

/**
 * deals with the storage of Tasks as a String and to
 * load it back from storage
 */
public class Storage {
    private String filePath;

    /**
     * initialises a storage with a filePath
     * @param filePath String This is the path to the file to store tasks
     */
    public Storage(String filePath) {

        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file and reconstructs them into a list.
     * If the file does not exist or is empty, an empty list is returned.
     * Malformed or empty lines within the file are skipped.
     *
     * @return A list of tasks reconstructed from the storage file.
     */
    //load from file
    public ArrayList<Task> load() {
        File file = new File(this.filePath);
        ArrayList<Task> list = new ArrayList<>();

        if (!file.exists() || file.length() == 0) {
            System.out.println("Seems like you're starting from scratch!");
        } else {
            try (Scanner scan = new Scanner(file)) {
                while (scan.hasNextLine()) {
                    String s = scan.nextLine().trim();
                    if (s.isEmpty()) {
                        continue;
                    }
                    String[] det = s.split("\\|", 2);
                    if (det.length < 2) {
                        continue;
                    }

                    Task forNow;
                    if (det[0].equalsIgnoreCase("T")) {
                        forNow = TodoTask.loadTask(det[1]);
                    } else if (det[0].equalsIgnoreCase("D")) {
                        forNow = DeadlineTask.loadTask(det[1]);
                    } else {
                        forNow = EventTask.loadTask(det[1]);
                    }
                    list.add(forNow);
                }
            } catch (FileNotFoundException e) {
                System.out.println("You gotta give me something to work off of, "
                        + "I cannot show you a file that doesn't exist!");
            }
        }
        return list;
    }

    /**
     * Saves a list of Task as stored strings
     * @param list This is the list of tasks to be saved
     */
    public void save(ArrayList<Task> list) {
        File file = new File(this.filePath);

        File parentDir = file.getParentFile();
        if(parentDir != null && !parentDir.exists()) {
            parentDir.mkdir();
        }
        try (FileWriter writer = new FileWriter(this.filePath)) {
            for (Task t: list) {
                writer.write(t.storeStr());
                writer.write(System.lineSeparator());
            }

        } catch (IOException e) {
            System.out.println("slow your roll! Give me time to communicate the plan");
        }
    }
}
