package command;

import element.StudyGroup;
import exception.EmptyCollectionException;
import interpreter.CollectionHandler;

import java.util.Map;

public class PrintAscending implements Command {

    @Override
    public String execute() {
        if (CollectionHandler.isEmpty()) throw new EmptyCollectionException();
        Map<Integer, StudyGroup> map = CollectionHandler.sortedByStudentsCount();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, StudyGroup> entry: map.entrySet()) {
            sb.append("\n").append(entry);
        }
        return sb.toString();
    }

    @Override
    public void setArgs(String arg) {}

    @Override
    public String getName() {
        return "print_ascending";
    }

    @Override
    public String help() {
        return getName() + " : вывести элементы коллекции в порядке возрастания";
    }

}
