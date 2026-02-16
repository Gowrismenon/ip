package Mal.logic;

import java.util.ArrayList;
import java.util.Arrays;

import Mal.storage.Storage;
import Mal.task.*;
import Mal.parser.Parser;
import Mal.ui.Ui;

/**
 * Acts as the main entry point for the Mal task management application.
 * This class initializes the user interface, storage system, and task list,
 * and manages the primary execution loop.
 */
public class Mal {
    /** The default file path used for data persistence. */
    private static final String PATH = "./Data/Mal.txt" ;

    private Storage storage;
    private TaskList taskList;
    private Ui ui;


    /**
     * Initializes the application by setting up the UI and storage.
     * Attempts to load existing tasks from the specified file path;
     * if loading fails, a new empty task list is initialized.
     *
     * @param PATH The file path where task data is stored and retrieved.
     */
    public Mal(String PATH) {
        ui = new Ui();
        storage = new Storage(PATH);
        try {
            taskList = new TaskList(storage.load());
        } catch (MalException e) {
            taskList = new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * consturctor for use by javaFX, to allow use of existing logic
     */
    public Mal() {
        ui = new Ui();
        storage = new Storage(PATH);
        try {
            taskList = new TaskList(storage.load());
        } catch (MalException e) {
            taskList = new TaskList(new ArrayList<Task>());
        }
    }


    /**
     * Processes the user input and returns Mal's response.
     * This replaces the old CLI 'run' loop.
     */
    public String getResponse(String input) {
        Parser p = new Parser(input);
        String command = p.command();

        if (command.equalsIgnoreCase("bye")) {
            storage.save(taskList.get());
            return "Bye. Hope to see you again soon... or whatever.";
        }

        String response = p.execute(taskList);

        return response;
    }

    /**
     * Serves as the main entry point to start the Mal application.
     * @param args Command line arguments (not used).
     * @throws MalException If an error occurs during program initialization.

    public static void main(String[] args) throws MalException {
        new Mal(PATH).run();
    }
    */

}


