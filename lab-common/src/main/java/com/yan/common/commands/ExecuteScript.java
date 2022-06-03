package com.yan.common.commands;

import com.yan.common.exception.NoArgumentException;
import com.yan.common.exception.EmptyCollectionException;
import com.yan.common.exception.BadArgumentException;
import com.yan.common.repository.CommandRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ExecuteScript implements Command {

    private File script = null;

    @Override
    public String execute() {
        if (script == null) {
            throw new NoArgumentException();
        }
        try {
            Scanner scanner = new Scanner(script);
            String[] line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine().trim().split(" ");
                if (line.length != 0) {
                    if (line[0].equals("execute_script"))  {
                        System.out.println("Cannot run execute_script from already executing script");
                        continue;
                    }
                    Command command = CommandRepository.getCommandMap().get(line[0]);
                    if (command == null) {
                        System.out.println("Command doesn't exist");
                        continue;
                    }
                    try {
                        if (line.length > 1) {
                            command.setArgs(line[1]);
                        }
                        CommandRepository.updateHistory(command);
                        return command.execute();
                    } catch (EmptyCollectionException | BadArgumentException e) {
                        return e.getMessage();
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "The end of script";
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
