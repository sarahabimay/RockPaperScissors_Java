import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ThrowTest {
    @Test
    public void verifyRockThrowIdentifier() {
        Throw rockThrow = Throw.ROCK;
        assertEquals(1, rockThrow.getIdentifier());
    }

    @Test
    public void verifyPaperThrowIdentifier() {
        Throw rockThrow = Throw.PAPER;
        assertEquals(2, rockThrow.getIdentifier());
    }

    @Test
    public void verifyScissorsThrowIdentifier() {
        Throw rockThrow = Throw.SCISSORS;
        assertEquals(3, rockThrow.getIdentifier());
    }
}
