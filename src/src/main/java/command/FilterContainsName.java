package command;

import element.StudyGroup;
import exception.EmptyCollectionException;
import exception.NoArgumentException;
import interpreter.CollectionHandler;

import java.util.Map;

public class FilterContainsName implements Command {

    private String arg = null;

    @Override
    public String execute() {
        if (arg == null || arg.equals("")) throw new NoArgumentException();
        if (CollectionHandler.isEmpty()) throw new EmptyCollectionException();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, StudyGroup> entry: CollectionHandler.getGroups().entrySet()) {
            if (entry.getValue().getName().contains(arg)) sb.append("\n").append(entry.getValue());
        }
        if (sb.toString().equals("")) return "No such elements";
        return sb.toString();
    }

    @Override
    public void setArgs(String arg) {
        this.arg = arg;
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
