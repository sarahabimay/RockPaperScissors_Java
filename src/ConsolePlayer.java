public class ConsolePlayer implements Player {
    private UserInterface userInterface;
    private Mark mark;

    public ConsolePlayer(UserInterface ui) {
        this.userInterface = ui;
    }

    public Mark getMark() {
        return mark;
    }

    public Mark generateMark() {
        mark = userInterface.requestConsoleMove();
        return mark;
    }
}
