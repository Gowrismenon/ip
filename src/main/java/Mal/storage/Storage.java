package Mal.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Mal.task.Task;
import Mal.task.TodoTask;
import Mal.task.DeadlineTask;
import Mal.task.EventTask;

/**
 * Deals with the storage of Tasks as a String and to
 * load it back from storage.
 */
public class Storage {
    private String filePath;

    /**
     * Initialises a storage with a filePath.
     * @param filePath String path to the file to store tasks.
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.trim().isEmpty() : "Storage initialized with invalid filePath";
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file and reconstructs them into a list.
     */
    public ArrayList<Task> load() {
        File file = new File(this.filePath);
        ArrayList<Task> list = new ArrayList<>();

        if (!file.exists() || file.length() == 0) {
            System.out.println("Seems like you're starting from scratch!");
            return list;
        }

        try (Scanner scan = new Scanner(file)) {
            while (scan.hasNextLine()) {
                processLine(scan.nextLine(), list);
            }
        } catch (FileNotFoundException e) {
            System.out.println("You gotta give me something to work off of, "
                    + "I cannot show you a file that doesn't exist!");
        }

        assert list != null : "Storage.load() is returning a null list";
        return list;
    }

    /**
     * Saves a list of Task as stored strings.
     * @param list This is the list of tasks to be saved.
     */
    public void save(ArrayList<Task> list) {
        ensureDirectoryExists();

        try (FileWriter writer = new FileWriter(this.filePath)) {
            for (Task t : list) {
                writer.write(t.storeStr() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Slow your roll! Give me time to communicate the plan");
        }
    }

    // --- Private Helper Methods (SLAP Improvements) ---

    private void processLine(String line, ArrayList<Task> list) {
        String data = line.trim();
        if (data.isEmpty()) {
            return;
        }

        String[] parts = data.split("\\|", 2);
        assert parts.length >= 2 : "Malformed line in storage file: " + data;

        if (parts.length >= 2) {
            list.add(reconstructTask(parts[0], parts[1]));
        }
    }

    private Task reconstructTask(String type, String taskData) {
        switch (type.toUpperCase()) {
        case "T":
            return TodoTask.loadTask(taskData);
        case "D":
            return DeadlineTask.loadTask(taskData);
        case "E":
            return EventTask.loadTask(taskData);
        default:
            assert false : "Unknown task type in storage: " + type;
            return null;
        }
    }

    private void ensureDirectoryExists() {
        File file = new File(this.filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
    }
}