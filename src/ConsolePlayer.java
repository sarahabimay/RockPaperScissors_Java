public class ConsolePlayer implements Player {
    private Throw consoleThrow;

    public ConsolePlayer(Throw aThrow) {
        this.consoleThrow = aThrow;
    }

    public Throw getThrow() {
        return consoleThrow;
    }
}
