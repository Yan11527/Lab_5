package com.yan.client.interpreter;


import com.yan.common.commands.Help;
import com.yan.common.commands.Info;
import com.yan.common.commands.Insert;
import com.yan.common.commands.Show;
import com.yan.common.commands.Update;
import com.yan.common.commands.RemoveKey;
import com.yan.common.commands.Clear;
import com.yan.common.commands.Save;
import com.yan.common.commands.Exit;
import com.yan.common.commands.ExecuteScript;
import com.yan.common.commands.History;
import com.yan.common.commands.ReplaceIfLower;
import com.yan.common.commands.RemoveLowerKey;
import com.yan.common.commands.RemoveAllByStudentsCount;
import com.yan.common.commands.FilterContainsName;
import com.yan.common.commands.PrintAscending;
import com.yan.common.repository.CollectionHandler;
import com.yan.common.repository.CommandRepository;

import java.util.Scanner;

public class CommandInterpreter {

    private final CollectionHandler handler;
    private final CommandRepository repository;

    public CommandInterpreter(CollectionHandler handler) {
        this.handler = handler;
        repository = new CommandRepository();
    }

    /**
     * Registering existing commands in invoker map
     */
    public void registerCommands() {
        repository.register(new Help());
        repository.register(new Info());
        repository.register(new Show());
        repository.register(new Insert());
        repository.register(new Update());
        repository.register(new RemoveKey());
        repository.register(new Clear());
        repository.register(new Save());
        repository.register(new ExecuteScript());
        repository.register(new Exit());
        repository.register(new History());
        repository.register(new ReplaceIfLower());
        repository.register(new RemoveLowerKey());
        repository.register(new RemoveAllByStudentsCount());
        repository.register(new FilterContainsName());
        repository.register(new PrintAscending());
    }

    /**
     * Reading commands and args from console and transferring to invoker for execution.
     * Printing the result of invocation
     */
    public void interactive() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter command (or help) -> ");
            if (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                if (!"".equals(s)) {
                    System.out.println(Invoker.execute(s.trim().split(" "), handler, repository));
                }
            }
        }
    }

}
