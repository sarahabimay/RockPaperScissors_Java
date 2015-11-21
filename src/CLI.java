import java.io.*;

public class CLI implements UserInterface {
    public final String GAME_GREETING = "Welcome to the Rock Paper Scissors Game.\n\n";
    public final String CONSOLE_MOVE_REQUEST = "Please enter Rock(1), Paper(2) or Scissors(3): \n";
    public final String CONSOLE_MOVE_DISPLAY = "You have selected: %s \n";
    public final String AI_MOVE_DISPLAY = "AI Player selected: %s \n";
    public final String RESULT_DISPLAY = "And the winner is: %s \n";
    public final String REPLAY_REQUEST = "Do you want to play again? Yes(1) or No(2): \n";
    private BufferedReader readStream;
    private PrintStream writeStream;
    private Game game;
    private Symbol consoleSymbol;

    public CLI(InputStream inputStream, PrintStream outputStream, Game game) {
        this.readStream = new BufferedReader(new InputStreamReader(inputStream));
        this.writeStream = outputStream;
        this.game = game;
        Symbol.setOrdinalToSymbol();
    }

    public String displayGreeting() {
        String greeting = GAME_GREETING;
        writeStream.println(greeting);
        return greeting;
    }

    public Symbol requestConsoleMove() {
        while (consoleSymbol == null) {
            writeStream.println(CONSOLE_MOVE_REQUEST);
            consoleSymbol = Symbol.getSymbolFromOrdinal(readInput());
        }
        return consoleSymbol;
    }

    public boolean requestReplay() {
        writeStream.println(REPLAY_REQUEST);
        return readInput() == 1;
    }

    public void displayResult() {
        writeStream.println(String.format(RESULT_DISPLAY, game.getWinner()));
    }

    public void displayConsoleMove(Symbol symbol) {
        writeStream.println(String.format(CONSOLE_MOVE_DISPLAY, symbol));
    }

    public void displayAIMove(Symbol symbol) {
        writeStream.println(String.format(AI_MOVE_DISPLAY, symbol));
    }

    public void play() {
        displayGreeting();
        displayConsoleMove(requestConsoleMove());
        playAgainstAI();
        displayResult();
        playAgain();
    }

    private Symbol playAgainstAI() {
        displayAIMove(game.generateAIMove());
        return game.passConsoleMoveToGameThenPlay(consoleSymbol);
    }

    private void playAgain() {
        if (requestReplay()) {
            reset();
            play();
        }
    }

    private void reset() {
        consoleSymbol = null;
        game = new Game(new Rules());
    }

    private int readInput() {
        try {
            return Integer.parseInt(readStream.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            return 0;
        }
        return 0;
    }
}