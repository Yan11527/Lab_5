package command;

import element.Coordinates;
import element.StudyGroup;
import exception.BadArgumentException;
import exception.EmptyCollectionException;
import exception.NoArgumentException;
import interpreter.CollectionHandler;
import util.InputReader;
import util.StringParser;

import java.util.Map;

public class Update implements Command {

    private Integer arg = null;
    private final StringParser parser = new StringParser();
    private final InputReader reader = new InputReader();

    @Override
    public String execute() {
        if (arg == null) throw new NoArgumentException();
        if (CollectionHandler.isEmpty()) throw new EmptyCollectionException();
        StudyGroup updated = null;
        for (Map.Entry<Integer, StudyGroup> entry: CollectionHandler.getGroups().entrySet()) {
            if (entry.getValue().getId().equals(arg)) updated = entry.getValue();
        }
        if (updated == null) return "No element found with such id";
        updated.setName(reader.readName());
        updated.setCoordinates(new Coordinates(reader.readX(), reader.readY()));
        updated.setStudentsCount(reader.readStudentsCount());
        updated.setShouldBeExpelled(reader.readShouldBeExpelled());
        updated.setTransferredStudents(reader.readTransferredStudents());
        updated.setFormOfEducation(reader.readFormOfEducation());
        updated.setGroupAdmin(reader.readPerson());
        return "Element updated successfully";
    }

    @Override
    public void setArgs(String arg) {
        this.arg = parser.parseInt(arg);
        if (this.arg == null) throw new BadArgumentException("Argument must be integer");
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String help() {
        return getName() + " id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }

}
