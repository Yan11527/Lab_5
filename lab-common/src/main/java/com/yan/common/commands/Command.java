package com.yan.common.commands;

import com.yan.common.repository.CollectionHandler;
import com.yan.common.repository.CommandRepository;

public interface Command {

    /**
     * Executing the logic of command
     * @return result of command execution
     */
    String execute(CollectionHandler handler, CommandRepository repository);

    /**
     * Setting the needed argument for command
     * @param arg string value argument to cast to required type
     */
    void setArgs(String arg);

    /**
     * Getting command name
     * @return command name
     */

    String getName();

    /**
     * For command help
     * @return description of using and result of command
     */
    String help();

}
