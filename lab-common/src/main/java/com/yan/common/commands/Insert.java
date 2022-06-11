package com.yan.common.commands;

import com.yan.common.element.Coordinates;
import com.yan.common.element.StudyGroup;
import com.yan.common.exception.BadArgumentException;
import com.yan.common.exception.NoArgumentException;
import com.yan.common.repository.CollectionHandler;
import com.yan.common.repository.CommandRepository;
import com.yan.common.util.InputReader;
import com.yan.common.util.StringParser;


public class Insert implements Command {

    private Integer key = null;
    private final StringParser parser = new StringParser();
    private final InputReader reader = new InputReader();

    @Override
    public String execute(CollectionHandler handler, CommandRepository repository) {
        if (key == null) {
            throw new NoArgumentException();
        }
        if (handler.containsKey(key)) {
            return "Element with such key already exists";
        }
        StudyGroup sg = new StudyGroup(reader.readName(), new Coordinates(reader.readX(), reader.readY()),
                reader.readStudentsCount(), reader.readShouldBeExpelled(), reader.readTransferredStudents(),
                reader.readFormOfEducation(), reader.readPerson());
        sg.setId(handler.getIdSeq());
        handler.add(key, sg);
        return "Element " + sg + "\nsuccessfully added";
    }

    @Override
    public void setArgs(String arg) {
        key = parser.parseInt(arg);
        if (key == null) {
            throw new BadArgumentException("Argument must be integer");
        }
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
