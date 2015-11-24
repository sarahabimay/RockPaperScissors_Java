import java.io.PrintStream;

public class CommandLineUI {
    public String GREETING_MESSAGE = "Welcome to the Rock Paper Scissors Game.\n\n";
    public String CONSOLE_MOVE_REQUEST = "Please enter Rock(1), Paper(2) or Scissors(3): \n";
    private PrintStream writeStream;

    public CommandLineUI(PrintStream printStream) {
        this.writeStream = printStream;
    }

    public void displayGreeting() {
        writeStream.println(GREETING_MESSAGE);
    }

    public void requestConsoleMove() {
        writeStream.println(CONSOLE_MOVE_REQUEST);
    }
}
