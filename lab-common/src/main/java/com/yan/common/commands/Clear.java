package com.yan.common.commands;

import com.yan.common.repository.CollectionHandler;
import com.yan.common.repository.CommandRepository;

public class Clear implements Command {

    @Override
    public String execute(CollectionHandler handler, CommandRepository repository) {
        handler.clear();
        return "Collection successfully cleared";
    }

    @Override
    public void setArgs(String arg) {

    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String help() {
        return getName() + " : очистить коллекцию";
    }

}
