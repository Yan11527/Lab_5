package com.yan.common.commands;

import com.yan.common.exception.BadArgumentException;
import com.yan.common.exception.EmptyCollectionException;
import com.yan.common.exception.NoArgumentException;
import com.yan.common.repository.CollectionHandler;
import com.yan.common.repository.CommandRepository;
import com.yan.common.util.StringParser;


public class RemoveLowerKey implements Command {

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
        int removed = handler.removeLowerKey(key);
        if (removed == 0) {
            return "No elements with lower key found";
        }
        return removed + " elements removed successfully";
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
        return "remove_lower_key";
    }

    @Override
    public String help() {
        return getName() + " key : удалить из коллекции все элементы, ключ которых меньше, чем заданный";
    }

}
