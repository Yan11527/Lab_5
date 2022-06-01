package command;

import element.Coordinates;
import element.FormOfEducation;
import element.Person;
import element.StudyGroup;
import exception.BadArgumentException;
import exception.EmptyCollectionException;
import exception.NoArgumentException;
import interpreter.CollectionHandler;
import util.InputReader;
import util.StringParser;

public class ReplaceIfLower implements Command {

    private Integer arg = null;
    private final StringParser parser = new StringParser();
    private final InputReader reader = new InputReader();

    @Override
    public String execute() {
        if (arg == null) throw new NoArgumentException();
        if (CollectionHandler.isEmpty()) throw new EmptyCollectionException();
        if (!CollectionHandler.containsKey(arg)) return "Element with such key doesn't exist";
        StudyGroup newSG = new StudyGroup(reader.readName(), new Coordinates(reader.readX(), reader.readY()),
                reader.readStudentsCount(), reader.readShouldBeExpelled(), reader.readTransferredStudents(),
                reader.readFormOfEducation(), reader.readPerson());
        StudyGroup replaced = CollectionHandler.getByKey(arg);
        if (replaced.getStudentsCount() <= newSG.getStudentsCount()) {
            StudyGroup.setCount(StudyGroup.getCount() - 1);
            return "New value is not lower than old. No changes";
        }
        CollectionHandler.add(arg, newSG);
        return "Element successfully replaced";
    }

    @Override
    public void setArgs(String arg) {
        this.arg = parser.parseInt(arg);
        if (arg == null) throw new BadArgumentException("Argument for this command must be integer");
    }

    @Override
    public String getName() {
        return "replace_if_lower";
    }

    @Override
    public String help() {
        return getName() + " key {element} : заменить значение по ключу, если новое значение меньше старого";
    }

}
