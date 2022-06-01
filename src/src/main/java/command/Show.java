package command;

import element.StudyGroup;
import interpreter.CollectionHandler;

import java.util.Map;

public class Show implements Command {

    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, StudyGroup> entry: CollectionHandler.getGroups().entrySet()) {
            sb.append("\n").append(entry);
        }
        return sb.toString();
    }

    @Override
    public void setArgs(String arg) {}

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String help() {
        return getName() + " : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

}
