package com.yan.common.commands;

import com.yan.common.element.StudyGroup;
import com.yan.common.exception.BadArgumentException;
import com.yan.common.exception.EmptyCollectionException;
import com.yan.common.exception.NoArgumentException;
import com.yan.common.repository.CollectionHandler;
import com.yan.common.repository.CommandRepository;
import com.yan.common.util.StringParser;


public class RemoveKey implements Command {

    private Integer key = null;
    private final StringParser parser = new StringParser();

    @Override
    public String execute(CollectionHandler handler, CommandRepository repository) {
        if (key == null) {
            throw new NoArgumentException();
        }
        if (handler.isEmpty()) {
            throw new EmptyCollectionException();
        }
        if (!handler.containsKey(key)) {
            return "Element with such key doesn't exist";
        }
        StudyGroup sg = handler.removeByKey(key);
        return "Element " + sg + "\nsuccessfully removed";
    }

    @Override
    public void setArgs(String arg) {
        key = parser.parseInt(arg);
        if (arg == null) {
            throw new BadArgumentException("Argument for this command must be integer");
        }
    }

    @Override
    public String getName() {
        return "remove_key";
    }

    @Override
    public String help() {
        return getName() + " key : удалить элемент из коллекции по его ключу";
    }

}
