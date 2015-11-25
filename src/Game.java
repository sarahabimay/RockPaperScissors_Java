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
        Throw consoleThrow = this.userInterface.requestConsoleMove();
        this.players.add(new ConsolePlayer(consoleThrow));
    }

    public List<Player> getPlayers() {
        return this.players;
    }

    public void createAIPlayer() {
        this.players.add(new AIPlayer());
    }
}
