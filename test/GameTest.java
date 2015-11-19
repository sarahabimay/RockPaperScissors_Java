import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {

    private Game game;

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    public void generateAIMove() {
        assertEquals(SYMBOL.class, game.generateAIMove().getClass());
    }

    @Test
    public void playHumanMoveAgainstAI() {
        SYMBOL humansMove = SYMBOL.PAPER;
        SYMBOL aisMove = SYMBOL.ROCK;
        assertEquals(SYMBOL.PAPER, game.play(humansMove, aisMove));
    }

    @Test
    public void playAGame() {
        SYMBOL humanMoveFromConsole = SYMBOL.PAPER;
        assertEquals(SYMBOL.class, game.startGame(humanMoveFromConsole).getClass());
    }
}
