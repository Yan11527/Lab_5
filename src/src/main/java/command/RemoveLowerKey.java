package command;

import element.StudyGroup;
import exception.BadArgumentException;
import exception.EmptyCollectionException;
import exception.NoArgumentException;
import interpreter.CollectionHandler;
import util.StringParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RemoveLowerKey implements Command {

    private Integer arg = null;
    private final StringParser parser = new StringParser();

    @Override
    public String execute() {
        if (arg == null) throw new NoArgumentException();
        if (CollectionHandler.isEmpty()) throw new EmptyCollectionException();
        List<Integer> keys = new ArrayList<>();
        for (Map.Entry<Integer, StudyGroup> entry: CollectionHandler.getGroups().entrySet())
            if (entry.getKey() < arg) keys.add(entry.getKey());
        if (keys.isEmpty()) return "No elements with lower key found";
        for (Integer i: keys) CollectionHandler.removeByKey(i);
        return "Elements removed successfully";
    }

    @Override
    public void setArgs(String arg) {
        this.arg = parser.parseInt(arg);
        if (arg == null) throw new BadArgumentException("Argument for this command must be integer");
    }

    @Override
    public String getName() {
        return "remove_lower_key";
    }

    @Override
    public String help() {
        return getName() + " key : удалить из коллекции все элементы, ключ которых меньше, чем заданный";
    }

}
