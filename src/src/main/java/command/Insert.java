package command;

import element.Coordinates;
import element.StudyGroup;
import exception.BadArgumentException;
import exception.NoArgumentException;
import interpreter.CollectionHandler;
import util.InputReader;
import util.StringParser;

import java.time.ZonedDateTime;

public class Insert implements Command {

    private Integer arg = null;
    private final StringParser parser = new StringParser();
    private final InputReader reader = new InputReader();

    @Override
    public String execute() {
        if (arg == null) throw new NoArgumentException();
        if (CollectionHandler.containsKey(arg)) return "Element with such key already exists";
        StudyGroup sg = new StudyGroup(reader.readName(), new Coordinates(reader.readX(), reader.readY()),
                reader.readStudentsCount(), reader.readShouldBeExpelled(), reader.readTransferredStudents(),
                reader.readFormOfEducation(), reader.readPerson());
        CollectionHandler.add(arg, sg);
        return "Element " + sg + "\nsuccessfully added";
    }

    @Override
    public void setArgs(String arg) {
        this.arg = parser.parseInt(arg);
        if (this.arg == null) throw new BadArgumentException("Argument must be integer");
    }

    @Override
    public String getName() {
        return "insert";
    }

    @Override
    public String help() {
        return getName() + " key {element} : добавить новый элемент с заданным ключом";
    }

}
