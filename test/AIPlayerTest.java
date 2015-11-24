import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AIPlayerTest {
    @Test
    public void generateThrow() {
        AIPlayer aiPlayer = new AIPlayer();
        assertEquals(true, isValidThrowType(aiPlayer.generateThrow()));
    }

    private boolean isValidThrowType(Throw aThrow) {
        return aThrow.equals(Throw.PAPER) || aThrow.equals(Throw.ROCK) || aThrow.equals(Throw.SCISSORS);
    }
}
