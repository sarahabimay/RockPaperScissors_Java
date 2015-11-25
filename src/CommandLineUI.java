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

    public Throw requestConsoleTurn() {
        writeStream.println("Please select Rock(1), Paper(2), or Scissors(3): \n");
        int consoleMove = readLine();
        writeStream.println(String.format("Your move was: %s", consoleMove));
        return convertToThrow(consoleMove).get();
    }

    private Optional<Throw> convertToThrow(int consoleMove) {
        for (Throw aThrow : Throw.values()) {
            if (aThrow.equals(consoleMove)){
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

    public boolean isValidThrow(int consoleMove) {
        return !convertToThrow(consoleMove).equals(Optional.empty());
    }
}
