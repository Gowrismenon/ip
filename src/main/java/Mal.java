import javax.xml.crypto.Data;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//basic file stuff done, now need to write the opnening logic,


public class Mal {
    private Storage storage;
    private TaskList TL;
    private Ui ui;

    private static final String PATH = "./Data/Mal.txt" ;
    public Mal(String PATH){
        ui = new Ui();
        storage = new Storage(PATH);
        try {
            TL = new TaskList(storage.load());
        } catch (MalException e) {
            ui.showLoadingError();
            TL = new TaskList(new ArrayList<Task>());
        }
    }

    public void run() {
        ArrayList<String> commands = new ArrayList<>(Arrays.asList("list",
                "mark <task no.>",
                "unmark <task no.>",
                "delete <task no.>,",
                "todo <taskname>",
                "deadline <taskname> / by <deadline>",
                "event <taskname> /from <start> /to <end>",
                "bye"));

        ui.showWelcome();
        boolean isExit = false;
        while(!isExit) {
            String input = ui.readCommand();
            Parser p = new Parser(input);
            String command = p.command();

            if(command.equalsIgnoreCase("bye")) {
                storage.save(TL.get());
                ui.showBye();
                isExit = true;
            } else {
                boolean success = p.execute(TL);
                if(!success) {
                    ui.showHelp(commands);
                } else {
                    if (command.equalsIgnoreCase("todo") ||
                            command.equalsIgnoreCase("deadline") ||
                            command.equalsIgnoreCase("event")) {
                        TL.afterAdd();
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws MalException{
        new Mal(PATH).run();
    }

}


