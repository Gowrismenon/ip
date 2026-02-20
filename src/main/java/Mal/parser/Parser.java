package Mal.parser;

import Mal.logic.*;
import Mal.task.*;
import Mal.ui.Ui;

/**
 * Deciphers user input and executes the corresponding commands.
 * This class serves as the bridge between raw user strings and the application logic.
 */
public class Parser {
    private String input;
    private String[] inputDetails;
    private boolean isOneWord;

    public Parser(String input) {
        assert input != null : "Parser initialized with null input";
        this.input = input;
        this.inputDetails = input.split(" ", 2);
        assert this.inputDetails.length > 0 : "inputDetails array is unexpectedly empty";
        this.isOneWord = false;
    }

    /**
     * gets the command from input
     * @return command word
     */
    public String command() {
        assert this.inputDetails != null : "inputDetails is null when calling command()";
        String command = this.inputDetails[0];
        if (inputDetails.length <= 1) {
            this.isOneWord = true;
        }
        return command;
    }

    /**
     * Executes the logic associated with the identified command.
     */
    public String execute(TaskList taskList) {
        String command = this.command().toLowerCase();

        switch (command) {
        case "list":
            return taskList.list();
        case "mark":
            return handleMark(taskList);
        case "unmark":
            return handleUnmark(taskList);
        case "delete":
            return handleDelete(taskList);
        case "todo":
            return handleTodo(taskList);
        case "deadline":
            return handleDeadline(taskList);
        case "event":
            return handleEvent(taskList);
        case "find":
            return handleFind(taskList);
        case "help":
            return Ui.showHelp();
        default:

            return "I don't know what that means. Is that an Auradon thing? "
                    + Ui.showHelp();
        }
    }
// private helper methods to avoid long methods
    private String handleMark(TaskList taskList) {
        if (isMissingDetails()) {
            return "Give me a number. I'm not a mind reader.";
        }
        int idx = parseIndex();
        if (isOutOfBounds(idx, taskList)) {
            return "Ah yes, Mal.task number 'that one'. A classic. Tragically fictional";
        }
        return taskList.mark(idx);
    }

    private String handleUnmark(TaskList taskList) {
        if (isMissingDetails()) {
            return "Unmark what? I need a number.";
        }
        int idx = parseIndex();
        if (isOutOfBounds(idx, taskList)) {
            return "Ah yes, Mal.task number 'that one'. A classic. Tragically fictional\n";
        }
        return taskList.unmark(idx);
    }

    private String handleDelete(TaskList taskList) {
        if (isMissingDetails()) {
            return "Delete what? Be specific.";
        }
        int idx = parseIndex();
        if (isOutOfBounds(idx, taskList)) {
            return "You can't delete what was never added";
        }
        return taskList.delete(idx);
    }

    private String handleTodo(TaskList taskList) {
        if (isMissingDetails()) {
            return "A todo needs a description, obviously.";
        }
        try {
            Task task = TodoTask.taskify(inputDetails[1]);
            if(handleDuplicates(task.getName(), taskList)) {
                return "My archives already contain this. Are you testing my memory?";

            }
            taskList.add(task);
            return taskList.afterAdd();
        } catch (MalException e) {
            return "Details. I need details. Magic has limits.";
        }


    }

    private String handleDeadline(TaskList taskList) {
        if (isMissingDetails()) {
            return "Deadline for what?" + Ui.showHelp();
        }
        try {
            Task task = DeadlineTask.taskify(inputDetails[1]);
            if(handleDuplicates(task.getName(), taskList)) {
                return "My archives already contain this. Are you testing my memory?";

            }
            taskList.add(task);
            return taskList.afterAdd();
        } catch (MalException e) {
            return "Details. I need details. Magic has limits. " +
                    "If you're trying to add dates, it should be in YYYY-MM-DD format";
        }
    }

    private String handleEvent(TaskList taskList) {
        if (isMissingDetails()) {
            return "Events need a time frame.";
        }
        try {
            Task task = EventTask.taskify(inputDetails[1]);
            if(handleDuplicates(task.getName(), taskList)) {
                return "My archives already contain this. Are you testing my memory?";

            }
            taskList.add(task);
            return taskList.afterAdd();
        } catch (MalException e) {
            return "Details. I need details. Magic has limits." +
                    "If you're trying to add dates, it should be in YYYY-MM-DD format";
        }
    }

    private String handleFind(TaskList taskList) {
        if (isMissingDetails()) {
            String[] responses = {"What am I supposed to find? Thin air?"
                    , " What exactly do you want to find? perhaps give me a name"
                    , " Urmm find [EMPTY_SPACE]? I found it"
                    , " Give me a name"
            };
            return responses[(int) Math.random() * responses.length];
        }
        return taskList.find(inputDetails[1]);
    }

    private boolean isMissingDetails() {
        return inputDetails.length <= 1 || inputDetails[1].trim().isEmpty();
    }

    private int parseIndex() {
        try {
            return Integer.parseInt(inputDetails[1]) - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private boolean isOutOfBounds(int idx, TaskList taskList) {
        return idx < 0 || idx >= taskList.get().size();
    }

    private boolean handleDuplicates(String name, TaskList taskList) {
        boolean isDuplicate = false;
        for (Task task: taskList.get()) {
            if(task.getName().equals(name)) {
                isDuplicate = true;
                break;
            }
        }
        return isDuplicate;
    }
}