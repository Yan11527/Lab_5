package com.yan.common.commands;

import com.yan.common.element.StudyGroup;
import com.yan.common.exception.EmptyCollectionException;
import com.yan.common.repository.CollectionHandler;

import java.util.Map;

public class PrintAscending implements Command {

    @Override
    public String execute() {
        if (CollectionHandler.isEmpty()) {
            throw new EmptyCollectionException();
        }
        Map<Integer, StudyGroup> sorted = CollectionHandler.sortedByStudentsCount();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, StudyGroup> entry: sorted.entrySet()) {
            sb.append("\n").append(entry);
        }
        return sb.toString();
    }

    @Override
    public void setArgs(String arg) {

    }

    @Override
    public String getName() {
        return "print_ascending";
    }

    @Override
    public String help() {
        return getName() + " : вывести элементы коллекции в порядке возрастания";
    }

}
