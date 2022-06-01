package command;

import exception.EmptyCollectionException;
import interpreter.CollectionHandler;
import interpreter.FileHandler;

import java.io.FileNotFoundException;

public class Save implements Command {

    @Override
    public String execute() {
        if (CollectionHandler.isEmpty()) throw new EmptyCollectionException();
        try {
            FileHandler.writeFile(CollectionHandler.getGroups());
        } catch (FileNotFoundException | SecurityException e) {
            return e.getMessage();
        }
        return "Collection successfully saved to file";
    }

    @Override
    public void setArgs(String arg) {}

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String help() {
        return getName() + " : сохранить коллекцию в файл";
    }

}
