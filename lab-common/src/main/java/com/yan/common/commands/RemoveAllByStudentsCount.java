package com.yan.common.commands;

import com.yan.common.element.StudyGroup;
import com.yan.common.exception.BadArgumentException;
import com.yan.common.exception.EmptyCollectionException;
import com.yan.common.exception.NoArgumentException;
import com.yan.common.repository.CollectionHandler;
import com.yan.common.util.StringParser;
import com.yan.common.util.Validator;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RemoveAllByStudentsCount implements Command {

    private Integer count = null;
    private final StringParser parser = new StringParser();
    private final Validator validator = new Validator();

    @Override
    public String execute() {
        if (count == null) {
            throw new NoArgumentException();
        }
        if (CollectionHandler.isEmpty()) {
            throw new EmptyCollectionException();
        }
        List<Integer> keys = new ArrayList<>();
        for (Map.Entry<Integer, StudyGroup> entry: CollectionHandler.getGroups().entrySet()) {
            if (entry.getValue().getStudentsCount().equals(count)) {
                keys.add(entry.getKey());
            }
        }
        if (keys.isEmpty()) {
            return "No elements with such studentsCount found";
        }
        for (Integer i: keys) {
            CollectionHandler.removeByKey(i);
        }
        return "Elements removed successfully";
    }

    @Override
    public void setArgs(String arg) {
        count = parser.parseInt(arg);
        if (arg == null || !validator.checkPositive("studentsCount", count.longValue())) {
            throw new BadArgumentException("Argument for this command must be positive integer");
        }
    }

    @Override
    public String getName() {
        return "remove_all_by_students_count";
    }

    @Override
    public String help() {
        return getName() + " studentsCount : удалить из коллекции все элементы, "
                + "значение поля studentsCount которого эквивалентно заданному";
    }

}
