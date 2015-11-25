import java.io.*;
import java.util.Optional;

import static java.util.Optional.of;

public class CommandLineUI {
    public String GREETING_MESSAGE = "Welcome to the Rock Paper Scissors Game.\n\n";
    public String CONSOLE_MOVE_REQUEST = "Please enter Rock(1), Paper(2) or Scissors(3): \n";
    public String CONSOLE_MOVE_DISPLAY = "You have selected: %s \n";
    public String AI_MOVE_DISPLAY = "AI Player selected: %s \n";
    public String RESULT_DISPLAY = "And the winner is: %s \n";
    public String REPLAY_REQUEST = "Do you want to play again? Yes(1) or No(2): \n";
    private PrintStream writeStream;
    private BufferedReader readStream;


    public CommandLineUI(InputStream inputStream, PrintStream printStream) {
        this.readStream = new BufferedReader(new InputStreamReader(inputStream));
        this.writeStream = printStream;
    }

    public void displayGreeting() {
        writeStream.println(GREETING_MESSAGE);
    }

    public Throw requestConsoleMove() {
        Optional<Throw> consoleThrow = Optional.empty();
        while (!isValid(consoleThrow)) {
            writeStream.println(CONSOLE_MOVE_REQUEST);
            consoleThrow = convertToThrow(readInput());
        }
        return consoleThrow.get();
    }

    public void displayConsoleMove(Throw throwType) {
        writeStream.println(String.format(CONSOLE_MOVE_DISPLAY, throwType));
    }

    public void displayAIMove(Throw aThrow) {
        writeStream.println(String.format(AI_MOVE_DISPLAY, aThrow));
    }

    public void displayResult(Throw aThrow) {
        writeStream.println(String.format(RESULT_DISPLAY, aThrow));
    }

    public ReplayOption requestReplay() {
        Optional<ReplayOption> choice = Optional.empty();
        while (!validReplayChoice(choice)) {
            writeStream.println(REPLAY_REQUEST);
            choice = convertToReplayOption(readInput());
        }
        return choice.get();
    }

    public void play() {
        displayGreeting();
        boolean replay = true;
        while (replay) {
            Throw aConsoleThrow = requestConsoleMove();
            displayConsoleMove(aConsoleThrow);
            Throw dummyAIThrow = dummySCISSORSAsAIThrow();
            displayAIMove(dummyAIThrow);
            Throw result = playAgainstAI(aConsoleThrow, dummyAIThrow);
            displayResult(result);
            replay = isPlayAgain(requestReplay());
        }
    }


    private boolean isPlayAgain(ReplayOption replayChoice) {
        return replayChoice == ReplayOption.REPLAY;
    }

    private Throw playAgainstAI(Throw aConsoleThrow, Throw dummyAIThrow) {
        Rules rules = new Rules();
        return rules.decideWinner(aConsoleThrow, dummyAIThrow).get();
    }

    private Throw dummySCISSORSAsAIThrow() {
        return Throw.SCISSORS;
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
        if (!consoleThrow.isPresent()) return false;
        for (Throw athrow : Throw.values()) {
            if (athrow.equals(consoleThrow.get())) {
                return true;
            }
        }
        return false;
    }

    private Optional<Throw> convertToThrow(int input) {
        for (Throw aThrow : Throw.values()) {
            if (aThrow.getIdentifier() == input) {
                return of(aThrow);
            }
        }
        return Optional.empty();
    }

    private boolean validReplayChoice(Optional<ReplayOption> choice) {
        if (!choice.isPresent()) return false;
        for (ReplayOption option : ReplayOption.values()) {
            if (option.equals(choice.get())) {
                return true;
            }
        }
        return false;
    }

    private Optional<ReplayOption> convertToReplayOption(int choice) {
        for (ReplayOption option : ReplayOption.values()) {
            if (option.optionNumber() == choice) {
                return of(option);
            }
        }
        return Optional.empty();
    }

    private enum ReplayOption {
        QUIT(1),
        REPLAY(2);

        private int optionNumber;

        ReplayOption(int choiceNumber) {
            this.optionNumber = choiceNumber;
        }

        public int optionNumber() {
            return optionNumber;
        }
    }
}
