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
        assertEquals(false, rules.decideWinner(Mark.PAPER, Mark.PAPER).isPresent());
    }

    @Test
    public void rockVScissorRule() {
        assertEquals(Mark.ROCK, rules.decideWinner(Mark.ROCK, Mark.SCISSORS).get());
    }

    @Test
    public void scissorVRockRule() {
        assertEquals(Mark.ROCK, rules.decideWinner(Mark.SCISSORS, Mark.ROCK).get());
    }

    @Test
    public void rockVPaperRule() {
        assertEquals(Mark.PAPER, rules.decideWinner(Mark.ROCK, Mark.PAPER).get());
    }

    @Test
    public void paperVRockRule() {
        assertEquals(Mark.PAPER, rules.decideWinner(Mark.PAPER, Mark.ROCK).get());
    }

    @Test
    public void scissorVPaperRule() {
        assertEquals(Mark.SCISSORS, rules.decideWinner(Mark.SCISSORS, Mark.PAPER).get());
    }

    @Test
    public void paperVScissorRule() {
        assertEquals(Mark.SCISSORS, rules.decideWinner(Mark.PAPER, Mark.SCISSORS).get());
    }
}
