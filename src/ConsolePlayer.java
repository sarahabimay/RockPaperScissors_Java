public class ConsolePlayer implements Player {
    private UserInterface userInterface;
    private Throw consoleThrow;

    public ConsolePlayer(UserInterface ui) {
        this.userInterface = ui;
    }

    public ConsolePlayer(Throw aThrow) {
        this.consoleThrow = aThrow;
    }

    public Throw getThrow() {
        return consoleThrow;
    }

    public Throw generateThrow() {
        consoleThrow = userInterface.requestConsoleMove();
        return consoleThrow;
    }
}
