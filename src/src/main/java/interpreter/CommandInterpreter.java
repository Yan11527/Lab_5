package interpreter;

import command.*;

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
                if (!s.equals("")) {
                    System.out.println(Invoker.execute(s.trim().split(" ")));
                }
            }
        }
    }

}
