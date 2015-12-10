import java.io.*;
import java.util.Optional;

import static java.util.Optional.of;

public class CommandLine implements UserInterface {
    public static final String GAME_OVER = "Game Over! \n";
    public static final String ANNOUNCE_DRAW = "The game is a draw!";
    public static final String WINNING_RESULT = "And the winner is: %s \n";
    public static final String AI_MOVE = "AI player has thrown: %s \n";
    public static final String CONSOLE_MOVE = "You have thrown: %s \n";
    public static final String INVALID_CHOICE = "Invalid selection. \n";
    public static final String REPLAY_OPTION = "Do you want to play again? Yes(1) or No(2): \n";
    public static final String THROW_CHOICE = "Please select Rock(1), Paper(2), or Scissors(3): \n";
    public static final String GREETING_PROMPT = "Rock Paper Scissors Game!\n";
    private PrintStream writeStream;
    private BufferedReader readStream;

    public CommandLine(InputStream inputStream, PrintStream printStream) {
        this.readStream = new BufferedReader(new InputStreamReader(inputStream));
        this.writeStream = printStream;
    }

    public void displayGreeting() {
        displayMessageToConsole(GREETING_PROMPT);
    }

    public Mark requestConsoleMove() {
        return getMoveFromConsole();
    }

    public void displayConsoleMove(Mark consoleMark) {
        displayMessageToConsole(String.format(CONSOLE_MOVE, consoleMark));
    }

    public void displayAIMove(Mark rock) {
        displayMessageToConsole(String.format(AI_MOVE, rock));
    }

    public void displayResult(Optional<Mark> result) {
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

    public boolean isValidMark(Optional<Mark> consoleMove) {
        return consoleMove.isPresent();
    }

    private Mark getMoveFromConsole() {
        Optional<Mark> mark = Optional.empty();
        while (!isValidMark(mark)) {
            displayMessageToConsole(THROW_CHOICE);
            mark = convertToMark(readLine());
            if (!isValidMark(mark)) {
                displayMessageToConsole(INVALID_CHOICE);
            }
        }
        return mark.get();
    }

    private Optional<Mark> convertToMark(int consoleMove) {
        for (Mark mark : Mark.values()) {
            if (mark.equalsChoice(consoleMove)) {
                return of(mark);
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

    private void announceWin(Optional<Mark> result) {
        displayMessageToConsole(String.format(WINNING_RESULT, result.get()));
    }

    private void announceGameOver() {
        displayMessageToConsole(GAME_OVER);
    }

    private void displayMessageToConsole(String message) {
        writeStream.println(message);
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
