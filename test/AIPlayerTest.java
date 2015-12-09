import org.junit.Test;

import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class AIPlayerTest {
    @Test
    public void generateScissorsThrow() {
        AIPlayer aiPlayer = new AIPlayer(new RandomFake(0.99));
        Throw move = aiPlayer.generateThrow();
        assertEquals(true, isValidThrowType(move));
        assertThat(aiPlayer.getThrow(), is(Throw.SCISSORS));
    }

    @Test
    public void generateRockThrow() {
        AIPlayer aiPlayer = new AIPlayer(new RandomFake(0.1));
        Throw move = aiPlayer.generateThrow();
        assertEquals(true, isValidThrowType(move));
        assertThat(aiPlayer.getThrow(), is(Throw.ROCK));
    }

    @Test
    public void generatePaperThrow() {
        AIPlayer aiPlayer = new AIPlayer(new RandomFake(0.5));
        Throw move = aiPlayer.generateThrow();
        assertEquals(true, isValidThrowType(move));
        assertThat(aiPlayer.getThrow(), is(Throw.PAPER));
    }

    private boolean isValidThrowType(Throw aThrow) {
        return aThrow.equals(Throw.PAPER) || aThrow.equals(Throw.ROCK) || aThrow.equals(Throw.SCISSORS);
    }

    private class RandomFake extends Random {
        private final double randomValue;

        public RandomFake(double randomFake) {
            this.randomValue = randomFake;
        }

        public double nextDouble() {
            return randomValue;
        }
    }
}
