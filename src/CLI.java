import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class CLI implements UserInterface {
    private PrintStream writeStream;
    private BufferedReader readStream;
    private Game game;
    private SYMBOL consoleSymbol;

    public CLI() {
        this.writeStream = new PrintStream(System.out);
        this.readStream = new BufferedReader(new InputStreamReader(System.in));
        this.game = new Game();
        SYMBOL.setOrdinalToSymbol();
    }

    public String displayGreeting() {
        String greeting = "Welcome to the Rock Paper Scissors Game.\n\n";
        writeStream.println(greeting);
        return greeting;
    }

    public SYMBOL requestConsoleMove() {
        while (consoleSymbol == null) {
            writeStream.println("Please enter Rock(1), Paper(2) or Scissors(3): \n");
            consoleSymbol = SYMBOL.getSymbolFromOrdinal(readInput());
        }
        return consoleSymbol;
    }

    public boolean requestReplay() {
        writeStream.println("Do you want to play again? Yes(1) or No(2): \n");
        return readInput() == 1;
    }

    public void displayResult() {
        writeStream.println(String.format("And the winner is: %s \n", game.getWinner()));
    }

    public void displayConsoleMove(SYMBOL symbol) {
        writeStream.println(String.format("You have selected: %s \n", symbol));
    }

    public void displayAIMove(SYMBOL symbol) {
        writeStream.println(String.format("AI Player selected: %s \n", symbol));
    }

    public void play() {
        displayGreeting();
        displayConsoleMove(requestConsoleMove());
        playAgainstAI();
        displayResult();
        playAgain();
    }

    private SYMBOL playAgainstAI() {
        displayAIMove(game.generateAIMove());
        return game.startGame(consoleSymbol);
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
