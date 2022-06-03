package com.yan.common.commands;

import com.yan.common.element.StudyGroup;
import com.yan.common.exception.BadArgumentException;
import com.yan.common.exception.EmptyCollectionException;
import com.yan.common.exception.NoArgumentException;
import com.yan.common.repository.CollectionHandler;
import com.yan.common.util.StringParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RemoveLowerKey implements Command {

    private Integer key = null;
    private final StringParser parser = new StringParser();

    @Override
    public String execute() {
        if (key == null) {
            throw new NoArgumentException();
        }
        if (CollectionHandler.isEmpty()) {
            throw new EmptyCollectionException();
        }
        List<Integer> keys = new ArrayList<>();
        for (Map.Entry<Integer, StudyGroup> entry: CollectionHandler.getGroups().entrySet()) {
            if (entry.getKey() < key) {
                keys.add(entry.getKey());
            }
        }
        if (keys.isEmpty()) {
            return "No elements with lower key found";
        }
        for (Integer i: keys) {
            CollectionHandler.removeByKey(i);
        }
        return "Elements removed successfully";
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
