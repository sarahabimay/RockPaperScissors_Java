import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PaperRuleTest {
    @Test
    public void paperNotOneOfPlayersMove() {
        PaperRule paperRule = new PaperRule();
        assertEquals(null, paperRule.decideWinner(SYMBOL.ROCK, SYMBOL.SCISSORS));
    }
    @Test
    public void paperBeatsRock() {
        PaperRule rockRule = new PaperRule();
        assertEquals(SYMBOL.ROCK, rockRule.decideWinner(SYMBOL.ROCK, SYMBOL.SCISSORS));
    }
}
