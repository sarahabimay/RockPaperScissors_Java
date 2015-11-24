import java.io.PrintStream;

public class CommandLineUI {
    public String GREETING_MESSAGE = "Welcome to the Rock Paper Scissors Game.\n\n";
    private PrintStream writeStream;

    public CommandLineUI(PrintStream printStream) {
        this.writeStream = printStream;
    }

    public void displayGreeting() {
        writeStream.println(GREETING_MESSAGE);
    }
}
