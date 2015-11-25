import java.util.ArrayList;
import java.util.List;

public class Game {
    private UserInterface userInterface;
    private List<Player> players;

    public Game(UserInterface userInterface) {
        this.userInterface = userInterface;
        this.players = new ArrayList<>();
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
}
