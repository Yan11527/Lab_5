package command;

import interpreter.Invoker;

public class Help implements Command {

    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder();
        for (Command c: Invoker.getCommandMap().values()) {
            sb.append("\n").append(c.help());
        }
        return sb.toString();
    }

    @Override
    public void setArgs(String arg) {}

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String help() {
        return getName() + " : вывести справку по доступным командам";
    }

}
