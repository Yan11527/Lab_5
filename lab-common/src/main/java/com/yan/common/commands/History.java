package com.yan.common.commands;

import com.yan.common.repository.CollectionHandler;
import com.yan.common.repository.CommandRepository;

public class History implements Command {

    @Override
    public String execute(CollectionHandler handler, CommandRepository repository) {
        StringBuilder sb = new StringBuilder("Last 8 commands:");
        for (String s: repository.getHistory()) {
            sb.append("\n").append(s);
        }
        return sb.toString();
    }

    @Override
    public void setArgs(String arg) {

    }

    @Override
    public String getName() {
        return "history";
    }

    @Override
    public String help() {
        return getName() + " : вывести последние 8 команд (без их аргументов)";
    }

}
