import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        askUIToDisplayGreeting();
        displayConsoleMove(createConsolePlayer().generateThrow());
        displayAIMove(createAIPlayer().generateThrow());
        askUIToDisplayResult(playGame());
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
        AIPlayer aiPlayer = new AIPlayer();
        this.players.add(aiPlayer);
        return aiPlayer;
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public Optional<Throw> throwPlayerMoves(Player player1, Player player2) {
        return rules.decideWinner(player1.getThrow(), player2.getThrow());
    }

    public void askUIToDisplayResult(Optional<Throw> result) {
        userInterface.displayResult(result);
    }

    void displayConsoleMove(Throw aThrow) {
        userInterface.displayConsoleMove(aThrow);
    }

    void displayAIMove(Throw aThrow) {
        userInterface.displayAIMove(aThrow);
    }

    private Optional<Throw> playGame() {
        return throwPlayerMoves(players.get(0), players.get(1));
    }
}
