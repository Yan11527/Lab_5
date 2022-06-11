package com.yan.common.commands;

import com.yan.common.repository.CollectionHandler;
import com.yan.common.repository.CommandRepository;


public class Info implements Command {

    @Override
    public String execute(CollectionHandler handler, CommandRepository repository) {
        return "Collection type: " + handler.getGroups().getClass() + "\n"
                + "Initialization date: " + handler.getInitDate() + "\n"
                + "Size: " + handler.getSize();
    }

    @Override
    public void setArgs(String arg) {
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String help() {
        return getName() + " : вывести в стандартный поток вывода информацию о коллекции "
                + "(тип, дата инициализации, количество элементов и т.д.)";
    }

}
