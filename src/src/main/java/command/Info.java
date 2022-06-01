package command;

import interpreter.CollectionHandler;

public class Info implements Command {

    @Override
    public String execute() {
        return "Collection type: " + CollectionHandler.getGroups().getClass() + "\n" +
                "Initialization date: " + CollectionHandler.getInitDate() + "\n" +
                "Size: " + CollectionHandler.getSize();
    }

    @Override
    public void setArgs(String arg) {}

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public String help() {
        return getName() + " : вывести в стандартный поток вывода информацию о коллекции " +
                "(тип, дата инициализации, количество элементов и т.д.)";
    }

}
