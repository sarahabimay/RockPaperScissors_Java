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
        assertEquals(Symbol.PAPER, rules.decideWinner(Symbol.PAPER, Symbol.PAPER));
    }

    @Test
    public void rockVScissorRule() {
        assertEquals(Symbol.ROCK, rules.decideWinner(Symbol.ROCK, Symbol.SCISSORS));
    }

    @Test
    public void scissorVRockRule() {
        assertEquals(Symbol.ROCK, rules.decideWinner(Symbol.SCISSORS, Symbol.ROCK));
    }

    @Test
    public void rockVPaperRule() {
        assertEquals(Symbol.PAPER, rules.decideWinner(Symbol.ROCK, Symbol.PAPER));
    }

    @Test
    public void paperVRockRule() {
        assertEquals(Symbol.PAPER, rules.decideWinner(Symbol.PAPER, Symbol.ROCK));
    }

    @Test
    public void scissorVPaperRule() {
        assertEquals(Symbol.SCISSORS, rules.decideWinner(Symbol.SCISSORS, Symbol.PAPER));
    }

    @Test
    public void paperVScissorRule() {
        assertEquals(Symbol.SCISSORS, rules.decideWinner(Symbol.PAPER, Symbol.SCISSORS));
    }
}
