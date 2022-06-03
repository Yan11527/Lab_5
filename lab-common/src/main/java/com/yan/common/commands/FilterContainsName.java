package com.yan.common.commands;

import com.yan.common.element.StudyGroup;
import com.yan.common.exception.NoArgumentException;
import com.yan.common.exception.EmptyCollectionException;
import com.yan.common.repository.CollectionHandler;

import java.util.Map;

public class FilterContainsName implements Command {

    private String str = null;

    @Override
    public String execute() {
        if (str == null || "".equals(str)) {
            throw new NoArgumentException();
        }
        if (CollectionHandler.isEmpty()) {
            throw new EmptyCollectionException();
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, StudyGroup> entry: CollectionHandler.getGroups().entrySet()) {
            if (entry.getValue().getName().contains(str)) {
                sb.append("\n").append(entry.getValue());
            }
        }
        if (sb.toString().equals("")) {
            return "No such elements";
        }
        return sb.toString();
    }

    @Override
    public void setArgs(String arg) {
        str = arg;
    }

    @Override
    public String getName() {
        return "filter_contains_name";
    }

    @Override
    public String help() {
        return getName() + " name : вывести элементы, значение поля name которых содержит заданную подстроку";
    }

}
