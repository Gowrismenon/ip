package Mal.logic;

import java.util.ArrayList;
import Mal.storage.Storage;
import Mal.task.Task;
import Mal.logic.MalException;
import Mal.parser.Parser;
import Mal.ui.Ui;

/**
 * Acts as the main entry point for the Mal task management application.
 * Initializes core components and handles user interaction logic.
 */
public class Mal {
    private static final String DEFAULT_FILE_PATH = "./Data/Mal.txt";

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for JavaFX. Uses the default file path.
     */
    public Mal() {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * Initializes the application components and loads data from the given path.
     *
     * @param filePath The file path where task data is stored.
     */
    public Mal(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        loadTaskList();

        checkInternalState();
    }

    /**
     * Loads the task list from storage. Initializes an empty list if loading fails.
     */
    private void loadTaskList() {
        try {
            taskList = new TaskList(storage.load());
        } catch (MalException e) {
            taskList = new TaskList(new ArrayList<Task>());
        }
    }

    /**
     * Verifies that all core components are properly initialized.
     */
    private void checkInternalState() {
        assert ui != null : "UI component failed to initialize";
        assert storage != null : "Storage component failed to initialize";
        assert taskList != null : "TaskList failed to initialize";
    }

    /**
     * Processes the user input and returns Mal's response.
     * Handles specific session-ending commands like 'bye'.
     *
     * @param input Raw user input string.
     * @return The response string from Mal.
     */
    public String getResponse(String input) {
        assert input != null : "Input cannot be null";

        Parser p = new Parser(input);
        String command = p.command();

        if (isTerminationCommand(command)) {
            return handleExit();
        }

        String response = p.execute(taskList);
        assert response != null : "Parser returned a null response";
        return response;
    }

    private boolean isTerminationCommand(String command) {
        return command.equalsIgnoreCase("bye");
    }

    private String handleExit() {
        storage.save(taskList.get());
        return "Bye. Hope to see you again soon... or whatever.";
    }
}
