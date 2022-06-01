package exception;

/**
 * Class-exception for notifying about argument absence in command
 */
public class NoArgumentException extends BadArgumentException {

    public NoArgumentException() {
        super("No argument found for this command");
    }

}
