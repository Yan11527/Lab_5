package interpreter;

import command.Command;
import exception.BadArgumentException;
import exception.EmptyCollectionException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Invoker {

    private static final Map<String, Command> commandMap = new HashMap<>();
    private static final LinkedList<String> history = new LinkedList<>();

    /**
     * Getting command map
     * @return map of available commands
     */
    public static Map<String, Command> getCommandMap() {
        return commandMap;
    }

    /**
     * Getting history of commands
     * @return list of last 8 executed commands
     */
    public static LinkedList<String> getHistory() {
        return history;
    }

    private static void updateHistory(Command command) {
        if (history.size() == 8) history.removeFirst();
        history.add(command.getName());
    }

    /**
     * Registering available command in map
     * @param command command-object to register
     */
    public static void register(Command command) {
        commandMap.put(command.getName(), command);
    }

    /**
     * Recognizing command by its name from console line, setting arguments and executing
     * @param commandLine string line from console
     * @return string result of command execution
     */
    public static String execute(String[] commandLine) {
        Command command = commandMap.get(commandLine[0]);
        if (command == null) return "Command doesn't exist";
        try {
            if (commandLine.length > 1) command.setArgs(commandLine[1]);
            updateHistory(command);
            return command.execute();
        } catch (EmptyCollectionException | BadArgumentException e) {
            return e.getMessage();
        }
    }

}
