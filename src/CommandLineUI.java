import java.io.*;
import java.util.Optional;

public class CommandLineUI {
    public String GREETING_MESSAGE = "Welcome to the Rock Paper Scissors Game.\n\n";
    public String CONSOLE_MOVE_REQUEST = "Please enter Rock(1), Paper(2) or Scissors(3): \n";
    public String CONSOLE_MOVE_DISPLAY = "You have selected: %s \n";
    private PrintStream writeStream;
    private BufferedReader readStream;

    public CommandLineUI(PrintStream printStream) {
        this.writeStream = printStream;
    }

    public CommandLineUI(InputStream inputStream, PrintStream printStream) {
        this.readStream = new BufferedReader(new InputStreamReader(inputStream));
        this.writeStream = printStream;
    }

    public void displayGreeting() {
        writeStream.println(GREETING_MESSAGE);
    }

    public Optional<Throw> requestConsoleMove() {
        Optional<Throw> consoleThrow = Optional.empty();
        while (!isValid(consoleThrow)) {
            writeStream.println(CONSOLE_MOVE_REQUEST);
            consoleThrow = convertToThrow(readInput());
        }
        return consoleThrow;
    }

    public void displayConsoleMove(Optional<Throw> throwType) {
        writeStream.println(String.format(CONSOLE_MOVE_DISPLAY, throwType.get()));
    }

    private int readInput() {
        try {
            return Integer.parseInt(readStream.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
        }
        return 0;
    }

    private boolean isValid(Optional<Throw> consoleThrow) {
        for (Throw athrow : Throw.values()) {
            boolean ispresent = consoleThrow.isPresent();
            if (consoleThrow.isPresent() && athrow.equals(consoleThrow.get())) {
                return true;
            }
        }
        return false;
    }

    private Optional<Throw> convertToThrow(int input) {
        for (Throw aThrow : Throw.values()) {
            if (aThrow.getIdentifier() == input) {
                return Optional.of(aThrow);
            }
        }
        return Optional.empty();
    }
}
