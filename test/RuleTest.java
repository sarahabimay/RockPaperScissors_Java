import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RuleTest {
    @Test
    public void sameSymbolVSameSymbolRule(){
        Rules rule = new Rules();
        assertEquals(null, rule.decideWinner(SYMBOL.PAPER, SYMBOL.PAPER));
    }

    @Test
    public void rockVScissorRule() {
        Rules rules = new Rules();
        assertEquals(SYMBOL.ROCK, rules.decideWinner(SYMBOL.ROCK, SYMBOL.SCISSORS));
    }

    @Test
    public void scissorVRockRule() {
        Rules rules = new Rules();
        assertEquals(SYMBOL.ROCK, rules.decideWinner(SYMBOL.SCISSORS,SYMBOL.ROCK));
    }
    @Test
    public void rockVPaperRule() {
        Rules rules = new Rules();
        assertEquals(SYMBOL.PAPER, rules.decideWinner(SYMBOL.ROCK, SYMBOL.PAPER));
    }
    @Test
    public void paperVRockRule() {
        Rules rules = new Rules();
        assertEquals(SYMBOL.PAPER, rules.decideWinner(SYMBOL.PAPER, SYMBOL.ROCK));
    }

}
