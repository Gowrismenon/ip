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
    public boolean execute(TaskList taskList) {
        String command = this.command();
        int len = this.inputDetails.length;
        int idx;
        Task curr;
        boolean isCorrect = false;

        switch (command.toLowerCase()) {
        case "list":
            taskList.list();
            isCorrect = true;
            break;
        case "mark":
            if (this.inputDetails.length <= 1) {
                break;
            } else {
                idx = Integer.parseInt(this.inputDetails[1]) - 1;
                if (idx < 0 || idx >= len) {
                    Ui.showError("Ah yes, Mal.task number 'that one'. A classic. Tragically fictional");
                } else {
                    taskList.mark(idx);
                }
                isCorrect = true;
                break;
            }
        case "unmark":
            idx = Integer.parseInt(this.inputDetails[1]) - 1;
            if (idx < 0 || idx >= len) {
                Ui.showError("Ah yes, Mal.task number 'that one'. A classic. Tragically fictional\n");
            } else {
                taskList.unmark(idx);
            }
            isCorrect = true;
            break;
        case "delete":
            idx = Integer.parseInt(this.inputDetails[1]) - 1;
            if (idx < 0 || idx >= this.inputDetails.length) {
                Ui.showError("You can't delete what was never added");
            } else {
                taskList.delete(idx);
            }
            isCorrect = true;
            break;
        case "todo":
            curr = TodoTask.taskify(this.inputDetails[1]);
            taskList.add(curr);
            isCorrect = true;
            break;
        case "deadline":
            curr = DeadlineTask.taskify(this.inputDetails[1]);
            taskList.add(curr);
            isCorrect = true;
            break;
        case "event":
            curr = EventTask.taskify(this.inputDetails[1]);
            taskList.add(curr);
            isCorrect = true;
            break;
        case "find":
            taskList.find(this.inputDetails[1]);
            isCorrect = true;
        default:
            // Checkstyle requires a default case even if it does nothing
            break;
        }
        return isCorrect;
    }
}
