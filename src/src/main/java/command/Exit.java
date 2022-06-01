package command;

public class Exit implements Command {

    @Override
    public String execute() {
        System.out.println("Exiting application...");
        System.exit(0);
        return null;
    }

    @Override
    public void setArgs(String arg) {}

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String help() {
        return getName() + " : завершить программу (без сохранения в файл)";
    }

}
