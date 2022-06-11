package com.yan.common.commands;

import com.yan.common.exception.BadArgumentException;
import com.yan.common.exception.EmptyCollectionException;
import com.yan.common.exception.NoArgumentException;
import com.yan.common.repository.CollectionHandler;
import com.yan.common.repository.CommandRepository;
import com.yan.common.util.StringParser;
import com.yan.common.util.Validator;


public class RemoveAllByStudentsCount implements Command {

    private Integer count = null;
    private final StringParser parser = new StringParser();
    private final Validator validator = new Validator();

    @Override
    public String execute(CollectionHandler handler, CommandRepository repository) {
        if (count == null) {
            throw new NoArgumentException();
        }
        if (handler.isEmpty()) {
            throw new EmptyCollectionException();
        }
        int removed = handler.removeByStudentsCount(count);
        if (removed == 0) {
            return "No elements with such studentsCount found";
        }
        return removed + " elements removed successfully";
    }

    @Override
    public void setArgs(String arg) {
        count = parser.parseInt(arg);
        if (arg == null || !validator.checkPositive("studentsCount", count.longValue())) {
            throw new BadArgumentException("Argument for this command must be positive integer");
        }
    }

    @Override
    public String getName() {
        return "remove_all_by_students_count";
    }

    @Override
    public String help() {
        return getName() + " studentsCount : удалить из коллекции все элементы, "
                + "значение поля studentsCount которого эквивалентно заданному";
    }

}
