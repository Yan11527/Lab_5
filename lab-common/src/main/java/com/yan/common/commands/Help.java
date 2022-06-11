package com.yan.common.commands;

import com.yan.common.repository.CollectionHandler;
import com.yan.common.repository.CommandRepository;

public class Help implements Command {

    @Override
    public String execute(CollectionHandler handler, CommandRepository repository) {
        StringBuilder sb = new StringBuilder();
        for (Command c: repository.getCommandMap().values()) {
            sb.append("\n").append(c.help());
        }
        return sb.toString();
    }

    @Override
    public void setArgs(String arg) {

    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String help() {
        return getName() + " : вывести справку по доступным командам";
    }

}
