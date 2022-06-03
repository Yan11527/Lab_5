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

import java.util.Scanner;

public class CommandInterpreter {

    /**
     * Registering existing commands in invoker map
     */
    public void registerCommands() {
        Invoker.register(new Help());
        Invoker.register(new Info());
        Invoker.register(new Show());
        Invoker.register(new Insert());
        Invoker.register(new Update());
        Invoker.register(new RemoveKey());
        Invoker.register(new Clear());
        Invoker.register(new Save());
        Invoker.register(new ExecuteScript());
        Invoker.register(new Exit());
        Invoker.register(new History());
        Invoker.register(new ReplaceIfLower());
        Invoker.register(new RemoveLowerKey());
        Invoker.register(new RemoveAllByStudentsCount());
        Invoker.register(new FilterContainsName());
        Invoker.register(new PrintAscending());
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
                    System.out.println(Invoker.execute(s.trim().split(" ")));
                }
            }
        }
    }

}
