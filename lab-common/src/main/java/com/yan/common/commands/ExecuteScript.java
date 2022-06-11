package com.yan.common.commands;

import com.yan.common.exception.NoArgumentException;
import com.yan.common.exception.EmptyCollectionException;
import com.yan.common.exception.BadArgumentException;
import com.yan.common.repository.CollectionHandler;
import com.yan.common.repository.CommandRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExecuteScript implements Command {

    private File script = null;

    @Override
    public String execute(CollectionHandler handler, CommandRepository repository) {
        if (script == null) {
            throw new NoArgumentException();
        }
        try (Scanner scanner = new Scanner(script)) {
            String[] line;
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                line = scanner.nextLine().trim().split(" ");
                if (line.length != 0) {
                    Command command = repository.getCommandMap().get(line[0]);
                    if (command == null) {
                        sb.append("Command doesn't exist").append("\n");
                        continue;
                    }
                    try {
                        if (line.length > 1) {
                            command.setArgs(line[1]);
                        }
                        repository.updateHistory(command);
                        sb.append(command.execute(handler, repository)).append("\n");
                    } catch (EmptyCollectionException | BadArgumentException e) {
                        sb.append(e.getMessage()).append("\n");
                    }
                }
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void setArgs(String arg) {
        script = new File(arg);
        if (!script.exists() || !script.canRead()) {
            throw new BadArgumentException("Wrong file path to script");
        }
    }

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String help() {
        return getName() + " file_name : считать и исполнить скрипт из указанного файла. "
                + "В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме";
    }

}
