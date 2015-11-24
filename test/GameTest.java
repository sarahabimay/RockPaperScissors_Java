import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {
    @Test
    public void playTheGameAgainstTwoPlayers() {
        ConsolePlayer consolePlayer = new ConsolePlayer(Throw.ROCK);
        FakeAIPlayer aiPlayer = new FakeAIPlayer();
        aiPlayer.nextThrow(Throw.PAPER);
        Game game = new Game(new Rules());
        assertEquals(Throw.PAPER, game.playGame(consolePlayer, aiPlayer));
    }
}
