import java.util.ArrayList;
import java.util.List;

public class FakeCLI implements UserInterface {
    private Game game = new Game();
    private boolean hasDisplayGreetingBeenCalled = false;
    private boolean hasRequestConsoleMoveBeenCalled = false;
    private boolean hasPlayBeenCalled = false;
    private List<SYMBOL> dummyMoves = new ArrayList<>();
    private SYMBOL consoleSymbol;
    private boolean hasResultBeenDisplayed = false;
    private boolean hasUserChoiceBeenShown = false;
    private boolean hasAIChoiceBeenShown = false;
    private boolean hasReplayChoiceBeenDisplayed = false;
    private int dummyReplayChoice = 0;

    public SYMBOL getConsoleMove() {
        return consoleSymbol;
    }

    public String displayGreeting() {
        hasDisplayGreetingBeenCalled = true;
        return "Welcome to the Rock Paper Scissors Game.";
    }

    public SYMBOL requestConsoleMove() {
        hasRequestConsoleMoveBeenCalled = true;
        consoleSymbol = dummyMoves.remove(0);
        return consoleSymbol;
    }

    public void displayResult() {
        hasResultBeenDisplayed = true;
    }

    public void displayConsoleMove(SYMBOL symbol) {
        hasUserChoiceBeenShown = true;
    }

    public void displayAIMove(SYMBOL symbol) {
        hasAIChoiceBeenShown = true;
    }

    public void play() {
        hasPlayBeenCalled = true;
        displayGreeting();
        displayConsoleMove(requestConsoleMove());
        playAgainstAI();
        displayResult();
        playAgain();
    }

    private void playAgainstAI() {
        displayAIMove(game.generateAIMove());
        game.startGame(consoleSymbol);
    }

    public boolean hasDisplayGreetingBeenCalled() {
        return hasDisplayGreetingBeenCalled;
    }

    public boolean hasRequestConsoleMoveBeenCalled() {
        return hasRequestConsoleMoveBeenCalled;
    }

    public boolean hasResultBeenDisplayed() {
        return hasResultBeenDisplayed;
    }

    public boolean hasUserChoiceBeenShown() {
        return hasUserChoiceBeenShown;
    }

    public boolean hasPlayBeenCalled() {
        return hasPlayBeenCalled;
    }

    public boolean hasAIChoiceBeenShown() {
        return hasAIChoiceBeenShown;
    }

    public boolean hasReplayChoiceBeenDisplayed() {
        return hasReplayChoiceBeenDisplayed;
    }

    public void addDummyMoves(List<SYMBOL> symbols) {
        dummyMoves = symbols;
    }

    public void dummyReplayChoice(int replayChoice) {
        dummyReplayChoice = replayChoice;
    }

    private void playAgain() {
        if (requestReplay()) {
            reset();
            play();
        }
    }

    private void reset() {
        consoleSymbol = null;
        game = new Game();
    }

    private boolean requestReplay() {
        hasReplayChoiceBeenDisplayed = true;
        if (dummyReplayChoice == 1) {
            dummyReplayChoice = 2;
            return true;
        }
        return false;
    }


}
