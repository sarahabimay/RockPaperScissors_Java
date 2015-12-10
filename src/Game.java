import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Game {
    private Rules rules;
    private UserInterface userInterface;
    private List<Player> players;

    public Game(UserInterface userInterface, Rules rules) {
        this.userInterface = userInterface;
        this.players = new ArrayList<>();
        this.rules = rules;
    }

    public void startGame() {
        boolean replay = true;
        while (replay) {
            askUIToDisplayGreeting();
            displayConsoleMove(createConsolePlayer().generateMark());
            displayAIMove(createAIPlayer().generateMark());
            askUIToDisplayResult(playGame());
            replay = playAgain();
        }
    }

    public void askUIToDisplayGreeting() {
        this.userInterface.displayGreeting();
    }

    public Player createConsolePlayer() {
        ConsolePlayer consolePlayer = new ConsolePlayer(userInterface);
        this.players.add(consolePlayer);
        return consolePlayer;
    }

    public Player createAIPlayer() {
        AIPlayer aiPlayer = new AIPlayer(new Random());
        this.players.add(aiPlayer);
        return aiPlayer;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public Optional<Mark> throwPlayerMoves(Player player1, Player player2) {
        return rules.decideWinner(player1.getMark(), player2.getMark());
    }

    public void askUIToDisplayResult(Optional<Mark> result) {
        userInterface.displayResult(result);
    }

    void displayConsoleMove(Mark mark) {
        userInterface.displayConsoleMove(mark);
    }

    void displayAIMove(Mark mark) {
        userInterface.displayAIMove(mark);
    }

    boolean playAgain() {
        this.players = new ArrayList<>();
        return userInterface.requestReplay();
    }

    private Optional<Mark> playGame() {
        return throwPlayerMoves(players.get(0), players.get(1));
    }
}
