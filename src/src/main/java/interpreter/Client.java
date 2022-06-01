package interpreter;

import java.io.IOException;
import java.util.Date;


public class Client {

    private FileHandler fileHandler;

    public Client(String filePath) {
        if (filePath == null) {
            System.out.println("!!! File path must be provided with environmental variable !!!\n" +
                    "Exiting application...");
            System.exit(0);
        }
        try {
            fileHandler = new FileHandler(filePath);
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\nExiting program...");
            System.exit(0);
        }
        try {
            CollectionHandler.setGroups(fileHandler.loadCollection());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Collection loaded from file successfully");
        CollectionHandler.setInitDate(new Date());
    }

    /**
     * Starting interactive application
     */
    public void run() {
        CommandInterpreter interpreter = new CommandInterpreter();
        interpreter.registerCommands();
        interpreter.interactive();
    }

}
