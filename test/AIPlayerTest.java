import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.isIn;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class AIPlayerTest {
    @Test
    public void generateThrow() {
        AIPlayer aiPlayer = new AIPlayer();
        Throw move = aiPlayer.generateThrow();
        assertEquals(true, isValidThrowType(move));
        assertThat(aiPlayer.getThrow(), isIn(Arrays.asList(Throw.ROCK, Throw.PAPER, Throw.SCISSORS)));
    }

    private boolean isValidThrowType(Throw aThrow) {
        return aThrow.equals(Throw.PAPER) || aThrow.equals(Throw.ROCK) || aThrow.equals(Throw.SCISSORS);
    }
}
