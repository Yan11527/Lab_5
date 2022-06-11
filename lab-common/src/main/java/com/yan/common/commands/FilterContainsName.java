package com.yan.common.commands;

import com.yan.common.element.StudyGroup;
import com.yan.common.exception.NoArgumentException;
import com.yan.common.exception.EmptyCollectionException;
import com.yan.common.repository.CollectionHandler;
import com.yan.common.repository.CommandRepository;

import java.util.Map;

public class FilterContainsName implements Command {

    private String str = null;

    @Override
    public String execute(CollectionHandler handler, CommandRepository repository) {
        if (str == null || "".equals(str)) {
            throw new NoArgumentException();
        }
        if (handler.isEmpty()) {
            throw new EmptyCollectionException();
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, StudyGroup> entry: handler.filterContainsName(str).entrySet()) {
            sb.append("\n").append(entry);
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
