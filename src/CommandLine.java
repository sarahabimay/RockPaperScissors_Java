import java.io.*;
import java.util.Optional;

import static java.util.Optional.of;

public class CommandLine implements UserInterface {
    public String GAME_OVER = "Game Over! \n";
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

    public CommandLine(InputStream inputStream, PrintStream printStream) {
        this.readStream = new BufferedReader(new InputStreamReader(inputStream));
        this.writeStream = printStream;
    }

    public void displayGreeting() {
        displayMessageToConsole(GREETING_PROMPT);
    }

    public Throw requestConsoleMove() {
        return getMoveFromConsole();
    }

    public void displayConsoleMove(Throw consoleThrow) {
        displayMessageToConsole(String.format(CONSOLE_MOVE, consoleThrow));
    }

    public void displayAIMove(Throw rock) {
        displayMessageToConsole(String.format(AI_MOVE, rock));
    }

    public void displayResult(Optional<Throw> result) {
        announceGameOver();
        if (result.isPresent()) {
            announceWin(result);
        } else {
            announceDraw();
        }
    }

    public boolean requestReplay() {
        ReplayOption replayOption = getReplayOption();
        return replayOption.equals(ReplayOption.REPLAY);
    }

    public boolean isValidThrow(Optional<Throw> consoleMove) {
        return consoleMove.isPresent();
    }

    private Throw getMoveFromConsole() {
        Optional<Throw> consoleThrow = Optional.empty();
        while (!isValidThrow(consoleThrow)) {
            displayMessageToConsole(THROW_CHOICE);
            consoleThrow = convertToThrow(readLine());
            if (!isValidThrow(consoleThrow)) {
                displayMessageToConsole(INVALID_CHOICE);
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

    private ReplayOption getReplayOption() {
        Optional<ReplayOption> replayOption = Optional.empty();
        while (!isValidReplayChoice(replayOption)) {
            displayMessageToConsole(REPLAY_OPTION);
            replayOption = convertToReplayOption(readLine());
            if (!isValidReplayChoice(replayOption)) {
                displayMessageToConsole(INVALID_CHOICE);
            }
            displayReplayChoice(replayOption);
        }
        return replayOption.get();
    }

    private void displayReplayChoice(Optional<ReplayOption> replayOption) {
        String prompt;
        if (replayOption.isPresent()) {
            prompt = replayOption.get().toString();
        } else {
            prompt = INVALID_CHOICE;
        }
        writeStream.println(String.format("%s \n", prompt));
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

    private void announceDraw() {
        displayMessageToConsole(ANNOUNCE_DRAW);
    }

    private void announceWin(Optional<Throw> result) {
        displayMessageToConsole(String.format(WINNING_RESULT, result.get()));
    }

    private void announceGameOver() {
        displayMessageToConsole(GAME_OVER);
    }

    private void displayMessageToConsole(String throw_choice) {
        writeStream.println(throw_choice);
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
