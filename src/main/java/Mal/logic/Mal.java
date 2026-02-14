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
     * Starts the main command loop of the application.
     * Continuously reads user input, deciphers commands using the Parser,
     * and updates the TaskList and Storage accordingly until the user exits.
     */
    public void run() {
        ArrayList<String> commands = new ArrayList<>(Arrays.asList("list",
                "mark <Mal.task no.>",
                "unmark <Mal.task no.>",
                "find <name>",
                "delete <Mal.task no.>,",
                "todo <taskname>",
                "deadline <taskname> / by <deadline>",
                "event <taskname> /from <start> /to <end>",
                "bye"));

        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String input = ui.readCommand();
            Parser p = new Parser(input);
            String command = p.command();

            if (command.equalsIgnoreCase("bye")) {
                storage.save(taskList.get());
                ui.showBye();
                isExit = true;
            } else {
                boolean success = p.execute(taskList);
                if (!success) {
                    ui.showHelp(commands);
                } else {
                    if (command.equalsIgnoreCase("todo")
                            || command.equalsIgnoreCase("deadline")
                            || command.equalsIgnoreCase("event")) {
                        taskList.afterAdd();
                    }
                }
            }
        }
    }

    /**
     * Serves as the main entry point to start the Mal application.
     * @param args Command line arguments (not used).
     * @throws MalException If an error occurs during program initialization.
     */
    public static void main(String[] args) throws MalException {
        new Mal(PATH).run();
    }

}


