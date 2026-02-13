public class Parser {
    private String input;
    private String[] inputDetails;
    private boolean isOneWord;

    public Parser(String input) {
        this.input = input;
        this.inputDetails = input.split(" ", 2);
        this.isOneWord = false;
    }

    public String command() {
        String command = this.inputDetails[0];
        if (inputDetails.length <= 1) {
            this.isOneWord = true;
        }
        return command;
    }

    public boolean execute(TaskList TL) {
        String command = this.command();
        int len = this.inputDetails.length;
        int idx;
        Task curr;
        boolean isCorrect = false;

        switch (command.toLowerCase()) {
            case "list":
                TL.list();
                isCorrect = true;
                break;
            case "mark":
                if(this.inputDetails.length <= 1) {
                    break;
                } else {
                    idx = Integer.parseInt(this.inputDetails[1]) - 1;
                    if (idx < 0 || idx >= len) {
                        Ui.showError("Ah yes, task number 'that one'. A classic. Tragically fictional");
                    } else {
                        TL.mark(idx);
                    }
                    isCorrect = true;
                    break;
                }

            case "unmark":
                idx = Integer.parseInt(this.inputDetails[1]) - 1;
                if (idx < 0 || idx >= len) {
                    Ui.showError("Ah yes, task number 'that one'. A classic. Tragically fictional\n");
                } else {
                    TL.mark(idx);
                }
                isCorrect = true;
                break;
            case "delete":
                idx = Integer.parseInt(this.inputDetails[1]) - 1;
                if (idx < 0 || idx >= this.inputDetails.length) {
                    Ui.showError("You can't delete what was never added");
                } else {
                    TL.delete(idx);
                }
                isCorrect = true;
                break;
            case "todo":
                curr = TodoTask.taskify(this.inputDetails[1]);
                TL.add(curr);
                isCorrect = true;
                break;
            case "deadline":
                curr = DeadlineTask.taskify(this.inputDetails[1]);
                TL.add(curr);
                isCorrect = true;
                break;
            case "event":
                curr = EventTask.taskify(this.inputDetails[1]);
                TL.add(curr);
                isCorrect = true;
                break;



        }
        return isCorrect;
    }
}
