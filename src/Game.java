import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Game {
    private Rules rules;
    private UserInterface userInterface;
    private List<Player> players;

    public Game(UserInterface userInterface) {
        this.userInterface = userInterface;
        this.players = new ArrayList<>();
    }

    public Game(UserInterface userInterface, Rules rules) {
        this.userInterface = userInterface;
        this.players = new ArrayList<>();
        this.rules = rules;
    }

    public void askUIToDisplayGreeting() {
        this.userInterface.displayGreeting();
    }

    public void obtainConsoleMoveAndDisplay() {
        ConsolePlayer consolePlayer = new ConsolePlayer(userInterface);
        consolePlayer.generateThrow();
        this.players.add(consolePlayer);
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public Player createAIPlayer() {
        AIPlayer aiPlayer = new AIPlayer();
        this.players.add(aiPlayer);
        return aiPlayer;
    }

    public Throw generateAIMove(Player aiPlayer) {
        return aiPlayer.generateThrow();
    }

    public Optional<Throw> throwPlayerMoves(Throw aThrow1, Throw aThrow2) {
        return rules.decideWinner(aThrow1, aThrow2);
    }

    public void askUIToDisplayResult(Optional<Throw> result) {
        userInterface.displayResult(result);
    }
}
