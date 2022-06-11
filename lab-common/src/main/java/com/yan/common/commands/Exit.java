package com.yan.common.commands;

import com.yan.common.repository.CollectionHandler;
import com.yan.common.repository.CommandRepository;

public class Exit implements Command {

    @Override
    public String execute(CollectionHandler handler, CommandRepository repository) {
        System.out.println("Exiting application...");
        System.exit(0);
        return null;
    }

    @Override
    public void setArgs(String arg) {

    }

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String help() {
        return getName() + " : завершить программу (без сохранения в файл)";
    }

}
