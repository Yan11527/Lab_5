package command;

import element.StudyGroup;
import exception.BadArgumentException;
import exception.EmptyCollectionException;
import exception.NoArgumentException;
import interpreter.CollectionHandler;
import util.StringParser;

public class RemoveKey implements Command {

    private Integer arg = null;
    private final StringParser parser = new StringParser();

    @Override
    public String execute() {
        if (arg == null) throw new NoArgumentException();
        if (CollectionHandler.isEmpty()) throw new EmptyCollectionException();
        if (!CollectionHandler.containsKey(arg)) return "Element with such key doesn't exist";
        StudyGroup sg = CollectionHandler.removeByKey(arg);
        return "Element " + sg + "\nsuccessfully removed";
    }

    @Override
    public void setArgs(String arg) {
        this.arg = parser.parseInt(arg);
        if (arg == null) throw new BadArgumentException("Argument for this command must be integer");
    }

    @Override
    public String getName() {
        return "remove_key";
    }

    @Override
    public String help() {
        return getName() + " key : удалить элемент из коллекции по его ключу";
    }

}
