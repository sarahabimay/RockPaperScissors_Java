import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RockRulesTest {

    private RockRule rockRule;

    @Before
    public void setUp() throws Exception {
        rockRule = new RockRule();
    }

    @Test
    public void rockNotOneOfPlayersMove() {
        assertEquals(null, rockRule.decideWinner(SYMBOL.PAPER, SYMBOL.PAPER));
    }

    @Test
    public void rockBeatsScissors1() {
        RockRule rockRule = new RockRule();
        assertEquals(SYMBOL.ROCK, rockRule.decideWinner(SYMBOL.ROCK, SYMBOL.SCISSORS));
    }

    @Test
    public void rockBeatsScissors2() {
        assertEquals(SYMBOL.ROCK, rockRule.decideWinner(SYMBOL.SCISSORS, SYMBOL.ROCK));
    }

    @Test
    public void rockLosesToPaper1() {
        assertEquals(SYMBOL.PAPER, rockRule.decideWinner(SYMBOL.ROCK, SYMBOL.PAPER));
    }

    @Test
    public void rockLosesToPaper2() {
        assertEquals(SYMBOL.PAPER, rockRule.decideWinner(SYMBOL.PAPER, SYMBOL.ROCK));
    }

    @Test
    public void tieWhenBothMovesAreRock() {
        assertEquals(SYMBOL.ROCK, rockRule.decideWinner(SYMBOL.ROCK, SYMBOL.ROCK));
    }
}
