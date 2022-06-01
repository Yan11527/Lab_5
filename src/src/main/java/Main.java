import interpreter.Client;

public class Main {

    public static void main(String[] args) {
        Client client = new Client(System.getenv("Groups"));
        client.run();
    }

}
