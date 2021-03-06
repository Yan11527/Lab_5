package com.yan.common.commands;

import com.yan.common.exception.EmptyCollectionException;
import com.yan.common.repository.CollectionHandler;
import com.yan.common.repository.CommandRepository;

import java.io.FileNotFoundException;

public class Save implements Command {

    @Override
    public String execute(CollectionHandler handler, CommandRepository repository) {
        if (handler.isEmpty()) {
            throw new EmptyCollectionException();
        }
        try {
            handler.saveCollection();
        } catch (FileNotFoundException | SecurityException e) {
            return e.getMessage();
        }
        return "Collection successfully saved to file";
    }

    @Override
    public void setArgs(String arg) {
    }

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String help() {
        return getName() + " : сохранить коллекцию в файл";
    }

}
