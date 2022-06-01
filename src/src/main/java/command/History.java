package command;

import interpreter.Invoker;

public class History implements Command {

    @Override
    public String execute() {
        StringBuilder sb = new StringBuilder("Last 8 commands:");
        for (String s: Invoker.getHistory()) {
            sb.append("\n").append(s);
        }
        return sb.toString();
    }

    @Override
    public void setArgs(String arg) {}

    @Override
    public String getName() {
        return "history";
    }

    @Override
    public String help() {
        return getName() + " : вывести последние 8 команд (без их аргументов)";
    }

}
