package com.yan.common.repository;

import com.yan.common.commands.Command;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public final class CommandRepository {

    private final Map<String, Command> commandMap = new HashMap<>();
    private final LinkedList<String> history = new LinkedList<>();
    private final int historySize = 8;


    /**
     * Getting command map
     * @return map of available commands
     */
    public Map<String, Command> getCommandMap() {
        return commandMap;
    }

    /**
     * Getting history of commands
     * @return list of last 8 executed commands
     */
    public LinkedList<String> getHistory() {
        return history;
    }

    public void updateHistory(Command command) {
        if (history.size() == historySize) {
            history.removeFirst();
        }
        history.add(command.getName());
    }

    /**
     * Registering available command in map
     * @param command command-object to register
     */
    public void register(Command command) {
        commandMap.put(command.getName(), command);
    }

}
