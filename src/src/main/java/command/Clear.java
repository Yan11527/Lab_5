package command;

import interpreter.CollectionHandler;


public class Clear implements Command {

    @Override
    public String execute() {
        CollectionHandler.clear();
        return "Collection successfully cleared";
    }

    @Override
    public void setArgs(String arg) {}

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String help() {
        return getName() + " : очистить коллекцию";
    }

}
