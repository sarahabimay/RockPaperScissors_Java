import java.io.*;
import java.util.Optional;

import static java.util.Optional.of;

public class CommandLineUI {
    private PrintStream writeStream;
    private BufferedReader readStream;

    public CommandLineUI(InputStream inputStream, PrintStream printStream) {
        this.readStream = new BufferedReader(new InputStreamReader(inputStream));
        this.writeStream = printStream;
    }

    public Optional<Throw> requestConsoleTurn() {
        int INVALID_MOVE = 9;
        int consoleMove = INVALID_MOVE;
        Optional<Throw> consoleThrow = Optional.empty();
        while (!isValidThrow(consoleMove)) {
            writeStream.println("Please select Rock(1), Paper(2), or Scissors(3): \n");
            consoleMove = readLine();
            writeStream.println(String.format("Your move was: %s", consoleMove));
            if (!isValidThrow(consoleMove)) {
                writeStream.println("Invalid choice. \n");
            }
            consoleThrow = convertToThrow(consoleMove);
        }
        return consoleThrow;
    }

    public boolean isValidThrow(int consoleMove) {
        return !convertToThrow(consoleMove).equals(Optional.empty());
    }

    public int requestReplay() {
        int INVALID_CHOICE = 9;
        int replayChoice = INVALID_CHOICE;
        while (!isValidReplayChoice(replayChoice )) {
            writeStream.println("Do you want to play again? Yes(1) or No(2): \n");
            replayChoice = readLine();
            if (!isValidReplayChoice(replayChoice)) {
                writeStream.println("Invalid selection: \n");
            }
        }
        return replayChoice;
    }

    private boolean isValidReplayChoice(int replayChoice) {
        return replayChoice == 1 || replayChoice == 2;
    }

    private Optional<Throw> convertToThrow(int consoleMove) {
        for (Throw aThrow : Throw.values()) {
            if (aThrow.equals(consoleMove)) {
                return of(aThrow);
            }
        }
        return Optional.empty();
    }

    private int readLine() {
        try {
            return Integer.parseInt(readStream.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
        }
        return 0;
    }

}
