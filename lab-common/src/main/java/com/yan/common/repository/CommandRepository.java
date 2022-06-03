package com.yan.common.repository;

import com.yan.common.commands.Command;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public final class CommandRepository {

    private static final Map<String, Command> COMMAND_MAP = new HashMap<>();
    private static final LinkedList<String> HISTORY = new LinkedList<>();
    private static final int HISTORY_SIZE = 8;

    private CommandRepository() {
    }

    /**
     * Getting command map
     * @return map of available commands
     */
    public static Map<String, Command> getCommandMap() {
        return COMMAND_MAP;
    }

    /**
     * Getting history of commands
     * @return list of last 8 executed commands
     */
    public static LinkedList<String> getHistory() {
        return HISTORY;
    }

    public static void updateHistory(Command command) {
        if (HISTORY.size() == HISTORY_SIZE) {
            HISTORY.removeFirst();
        }
        HISTORY.add(command.getName());
    }

    /**
     * Registering available command in map
     * @param command command-object to register
     */
    public static void register(Command command) {
        COMMAND_MAP.put(command.getName(), command);
    }

}
