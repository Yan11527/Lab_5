package com.yan.common.commands;


import com.yan.common.element.Coordinates;
import com.yan.common.element.StudyGroup;
import com.yan.common.exception.BadArgumentException;
import com.yan.common.exception.EmptyCollectionException;
import com.yan.common.exception.NoArgumentException;
import com.yan.common.repository.CollectionHandler;
import com.yan.common.repository.CommandRepository;
import com.yan.common.util.InputReader;
import com.yan.common.util.StringParser;

public class ReplaceIfLower implements Command {

    private Integer key = null;
    private final StringParser parser = new StringParser();
    private final InputReader reader = new InputReader();

    @Override
    public String execute(CollectionHandler handler, CommandRepository repository) {
        if (key == null) {
            throw new NoArgumentException();
        }
        if (handler.isEmpty()) {
            throw new EmptyCollectionException();
        }
        if (!handler.containsKey(key)) {
            return "Element with such key doesn't exist";
        }
        StudyGroup newSG = new StudyGroup(reader.readName(), new Coordinates(reader.readX(), reader.readY()),
                reader.readStudentsCount(), reader.readShouldBeExpelled(), reader.readTransferredStudents(),
                reader.readFormOfEducation(), reader.readPerson());
        boolean success = handler.replaceIfLower(key, newSG);
        if (!success) {
            return "New value is not lower than old. No changes";
        }
        return "Element successfully replaced";
    }

    @Override
    public void setArgs(String arg) {
        key = parser.parseInt(arg);
        if (arg == null) {
            throw new BadArgumentException("Argument for this command must be integer");
        }
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
