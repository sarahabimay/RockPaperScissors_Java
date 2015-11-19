import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RuleTest {

    private Rules rules;

    @Before
    public void setUp() {
        rules = new Rules();
    }

    @Test
    public void sameSymbolResultsInTie() {
        assertEquals(SYMBOL.PAPER, rules.decideWinner(SYMBOL.PAPER, SYMBOL.PAPER));
    }

    @Test
    public void rockVScissorRule() {
        assertEquals(SYMBOL.ROCK, rules.decideWinner(SYMBOL.ROCK, SYMBOL.SCISSORS));
    }

    @Test
    public void scissorVRockRule() {
        assertEquals(SYMBOL.ROCK, rules.decideWinner(SYMBOL.SCISSORS, SYMBOL.ROCK));
    }

    @Test
    public void rockVPaperRule() {
        assertEquals(SYMBOL.PAPER, rules.decideWinner(SYMBOL.ROCK, SYMBOL.PAPER));
    }

    @Test
    public void paperVRockRule() {
        assertEquals(SYMBOL.PAPER, rules.decideWinner(SYMBOL.PAPER, SYMBOL.ROCK));
    }

    @Test
    public void scissorVPaperRule() {
        assertEquals(SYMBOL.SCISSORS, rules.decideWinner(SYMBOL.SCISSORS, SYMBOL.PAPER));
    }

    @Test
    public void paperVScissorRule() {
        assertEquals(SYMBOL.SCISSORS, rules.decideWinner(SYMBOL.PAPER, SYMBOL.SCISSORS));
    }
}
