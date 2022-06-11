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

import java.util.Map;

public class Update implements Command {

    private Integer id = null;
    private final StringParser parser = new StringParser();
    private final InputReader reader = new InputReader();

    @Override
    public String execute(CollectionHandler handler, CommandRepository repository) {
        if (id == null) {
            throw new NoArgumentException();
        }
        if (handler.isEmpty()) {
            throw new EmptyCollectionException();
        }
        StudyGroup updated = null;
        for (Map.Entry<Integer, StudyGroup> entry: handler.getGroups().entrySet()) {
            if (entry.getValue().getId().equals(id)) {
                updated = entry.getValue();
            }
        }
        if (updated == null) {
            return "No element found with such id";
        }
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
        id = parser.parseInt(arg);
        if (id == null) {
            throw new BadArgumentException("Argument must be integer");
        }
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
