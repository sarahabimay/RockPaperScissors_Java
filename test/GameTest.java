import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {

    private Game game;

    @Before
    public void setUp() {
        game = new Game(new Rules());
    }

    @Test
    public void generateAIMove() {
        assertEquals(Symbol.class, game.generateAIMove().getClass());
    }

    @Test
    public void playHumanMoveAgainstAI() {
        Symbol humansMove = Symbol.PAPER;
        Symbol aisMove = Symbol.ROCK;
        assertEquals(Symbol.PAPER, game.play(humansMove, aisMove));
    }

    @Test
    public void playAGame() {
        Symbol humanMoveFromConsole = Symbol.PAPER;
        game.generateAIMove();
        assertEquals(Symbol.class, game.passConsoleMoveToGameThenPlay(humanMoveFromConsole).getClass());
    }
}
