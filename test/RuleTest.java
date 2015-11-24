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
        assertEquals(Throw.PAPER, rules.decideWinner(Throw.PAPER, Throw.PAPER));
    }

    @Test
    public void rockVScissorRule() {
        assertEquals(Throw.ROCK, rules.decideWinner(Throw.ROCK, Throw.SCISSORS));
    }

    @Test
    public void scissorVRockRule() {
        assertEquals(Throw.ROCK, rules.decideWinner(Throw.SCISSORS, Throw.ROCK));
    }

    @Test
    public void rockVPaperRule() {
        assertEquals(Throw.PAPER, rules.decideWinner(Throw.ROCK, Throw.PAPER));
    }

    @Test
    public void paperVRockRule() {
        assertEquals(Throw.PAPER, rules.decideWinner(Throw.PAPER, Throw.ROCK));
    }

    @Test
    public void scissorVPaperRule() {
        assertEquals(Throw.SCISSORS, rules.decideWinner(Throw.SCISSORS, Throw.PAPER));
    }

    @Test
    public void paperVScissorRule() {
        assertEquals(Throw.SCISSORS, rules.decideWinner(Throw.PAPER, Throw.SCISSORS));
    }
}
