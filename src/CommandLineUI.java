import java.io.*;
import java.util.Optional;

import static java.util.Optional.of;

public class CommandLineUI {
    public String ANNOUNCE_DRAW = "The game is a draw!";
    public String WINNING_RESULT = "And the winner is: %s \n";
    public String AI_MOVE = "AI player has thrown: %s \n";
    public String CONSOLE_MOVE = "You have thrown: %s \n";
    public String INVALID_CHOICE = "Invalid selection. \n";
    public String REPLAY_OPTION = "Do you want to play again? Yes(1) or No(2): \n";
    public String THROW_CHOICE = "Please select Rock(1), Paper(2), or Scissors(3): \n";
    public String GREETING_PROMPT = "Rock Paper Scissors Game!\n";
    private PrintStream writeStream;
    private BufferedReader readStream;

    public CommandLineUI(InputStream inputStream, PrintStream printStream) {
        this.readStream = new BufferedReader(new InputStreamReader(inputStream));
        this.writeStream = printStream;
    }

    public void displayGreeting() {
        writeStream.println(GREETING_PROMPT);
    }

    public Throw requestConsoleTurn() {
        Throw consoleThrow = getMoveFromConsole();
        displayConsoleMove(consoleThrow);
        return consoleThrow;
    }

    public boolean requestReplay() {
        Optional<ReplayOption> replayOption = getReplayOption();
        return replayOption.isPresent();
    }

    public boolean isValidThrow(Optional<Throw> consoleMove) {
        return consoleMove.isPresent();
    }

    private void displayConsoleMove(Throw consoleThrow) {
        writeStream.println(String.format(CONSOLE_MOVE, consoleThrow));
    }

    private Throw getMoveFromConsole() {
        Optional<Throw> consoleThrow = Optional.empty();
        while (!isValidThrow(consoleThrow)) {
            writeStream.println(THROW_CHOICE);
            consoleThrow = convertToThrow(readLine());
            if (!isValidThrow(consoleThrow)) {
                writeStream.println(INVALID_CHOICE);
            }
        }
        return consoleThrow.get();
    }

    private Optional<Throw> convertToThrow(int consoleMove) {
        for (Throw aThrow : Throw.values()) {
            if (aThrow.equalsChoice(consoleMove)) {
                return of(aThrow);
            }
        }
        return Optional.empty();
    }

    private Optional<ReplayOption> getReplayOption() {
        Optional<ReplayOption> replayOption = Optional.empty();
        while (!isValidReplayChoice(replayOption)) {
            writeStream.println(REPLAY_OPTION);
            replayOption = convertToReplayOption(readLine());
            if (!isValidReplayChoice(replayOption)) {
                writeStream.println(INVALID_CHOICE);
            }
        }
        return replayOption;
    }

    private boolean isValidReplayChoice(Optional<ReplayOption> replayOption) {
        return replayOption.isPresent();
    }

    private Optional<ReplayOption> convertToReplayOption(int replayChoice) {
        for (ReplayOption option : ReplayOption.values()) {
            if (option.equalsChoice(replayChoice)) {
                return of(option);
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

    public void displayAIMove(Throw rock) {
        writeStream.println(String.format(AI_MOVE, rock));
    }

    public void displayResult(Optional<Throw> result) {
        if (result.isPresent()){
            writeStream.println(String.format(WINNING_RESULT, result));
        }
        else{
            writeStream.println(ANNOUNCE_DRAW);
        }
    }

    private enum ReplayOption {
        REPLAY(1),
        QUIT(2);

        private int choiceOption;

        ReplayOption(int choiceOption) {
            this.choiceOption = choiceOption;
        }

        public boolean equalsChoice(int choice) {
            return choiceOption == choice;
        }
    }
}
