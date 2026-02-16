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

    /**
     * Initializes a Parser with the raw user input.
     * Splits the input into a command word and subsequent details.
     *
     * @param input The full line of text entered by the user.
     */
    public Parser(String input) {
        this.input = input;
        this.inputDetails = input.split(" ", 2);
        this.isOneWord = false;
    }

    /**
     * Extracts and returns the primary command word from the input.
     * Updates the internal state to reflect if the input consists of only one word.
     *
     * @return The first word of the user input in string format.
     */
    public String command() {
        String command = this.inputDetails[0];
        if (inputDetails.length <= 1) {
            this.isOneWord = true;
        }
        return command;
    }

    /**
     * Executes the logic associated with the identified command.
     * This method handles task creation, deletion, marking, and listing by
     * interacting with the provided TaskList.
     *
     * @param taskList The TaskList object where tasks are managed.
     * @return True if the command was recognized and executed, false otherwise.
     */
    public String execute(TaskList taskList) {
        String command = this.command();
        int len = taskList.get().size(); // Use actual list size for bounds checking
        int idx;
        Task curr;

        switch (command.toLowerCase()) {
        case "list":
            return taskList.list(); // Assumes list() will return a String soon

        case "mark":
            if (this.inputDetails.length <= 1) {
                return "Give me a number. I'm not a mind reader.";
            }
            idx = Integer.parseInt(this.inputDetails[1]) - 1;
            if (idx < 0 || idx >= len) {
                return "Ah yes, Mal.task number 'that one'. A classic. Tragically fictional";
            }
            return taskList.mark(idx);

        case "unmark":
            if (this.inputDetails.length <= 1) {
                return "Unmark what? I need a number.";
            }
            idx = Integer.parseInt(this.inputDetails[1]) - 1;
            if (idx < 0 || idx >= len) {
                return "Ah yes, Mal.task number 'that one'. A classic. Tragically fictional\n";
            }
            return taskList.unmark(idx);

        case "delete":
            if (this.inputDetails.length <= 1) {
                return "Delete what? Be specific.";
            }
            idx = Integer.parseInt(this.inputDetails[1]) - 1;
            if (idx < 0 || idx >= len) {
                return "You can't delete what was never added";
            }
            return taskList.delete(idx);

        case "todo":
            if (this.inputDetails.length <= 1) return "A todo needs a description, obviously.";
            curr = TodoTask.taskify(this.inputDetails[1]);
            taskList.add(curr);
            return taskList.afterAdd();

        case "deadline":
            if (this.inputDetails.length <= 1) return "Deadlines need a date. Don't be lazy.";
            curr = DeadlineTask.taskify(this.inputDetails[1]);
            taskList.add(curr);
            return taskList.afterAdd();

        case "event":
            if (this.inputDetails.length <= 1) return "Events need a time frame.";
            curr = EventTask.taskify(this.inputDetails[1]);
            taskList.add(curr);
            return taskList.afterAdd();

        case "find":
            if (this.inputDetails.length <= 1) return "What am I supposed to find? Thin air?";
            return taskList.find(this.inputDetails[1]);

        default:
            return "I don't know what that means. Is that an Auradon thing?";
        }
    }
}
