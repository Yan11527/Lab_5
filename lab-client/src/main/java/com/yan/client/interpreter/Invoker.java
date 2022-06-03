package com.yan.client.interpreter;

import com.yan.common.commands.Command;
import com.yan.common.exception.BadArgumentException;
import com.yan.common.exception.EmptyCollectionException;
import com.yan.common.repository.CommandRepository;

public final class Invoker {

    private Invoker() {
    }

    private static void updateHistory(Command command) {
        CommandRepository.updateHistory(command);
    }

    /**
     * Registering available command in map
     * @param command command-object to register
     */
    public static void register(Command command) {
        CommandRepository.register(command);
    }

    /**
     * Recognizing command by its name from console line, setting arguments and executing
     * @param commandLine string line from console
     * @return string result of command execution
     */
    public static String execute(String[] commandLine) {
        Command command = CommandRepository.getCommandMap().get(commandLine[0]);
        if (command == null) {
            return "Command doesn't exist";
        }
        try {
            if (commandLine.length > 1) {
                command.setArgs(commandLine[1]);
            }
            updateHistory(command);
            return command.execute();
        } catch (EmptyCollectionException | BadArgumentException e) {
            return e.getMessage();
        }
    }

}
