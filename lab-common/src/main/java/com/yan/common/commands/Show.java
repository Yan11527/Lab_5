package com.yan.common.commands;

import com.yan.common.element.StudyGroup;
import com.yan.common.repository.CollectionHandler;
import com.yan.common.repository.CommandRepository;

import java.util.Map;

public class Show implements Command {

    @Override
    public String execute(CollectionHandler handler, CommandRepository repository) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, StudyGroup> entry: handler.getGroups().entrySet()) {
            sb.append("\n").append(entry);
        }
        return sb.toString();
    }

    @Override
    public void setArgs(String arg) {
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String help() {
        return getName() + " : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

}
