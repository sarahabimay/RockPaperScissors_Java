import org.junit.Test;

import java.util.Random;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class AIPlayerTest {
    @Test
    public void generateScissorsThrow() {
        AIPlayer aiPlayer = new AIPlayer(new RandomValue(0.99));
        Mark move = aiPlayer.generateMark();
        assertEquals(true, isValidMarkType(move));
        assertThat(aiPlayer.getMark(), is(Mark.SCISSORS));
    }

    @Test
    public void generateRockThrow() {
        AIPlayer aiPlayer = new AIPlayer(new RandomValue(0.1));
        Mark move = aiPlayer.generateMark();
        assertEquals(true, isValidMarkType(move));
        assertThat(aiPlayer.getMark(), is(Mark.ROCK));
    }

    @Test
    public void generatePaperThrow() {
        AIPlayer aiPlayer = new AIPlayer(new RandomValue(0.5));
        Mark move = aiPlayer.generateMark();
        assertEquals(true, isValidMarkType(move));
        assertThat(aiPlayer.getMark(), is(Mark.PAPER));
    }

    private boolean isValidMarkType(Mark mark) {
        return mark.equals(Mark.PAPER) || mark.equals(Mark.ROCK) || mark.equals(Mark.SCISSORS);
    }

    private class RandomValue extends Random {
        private final double randomValue;

        public RandomValue(double randomFake) {
            this.randomValue = randomFake;
        }

        @Override
        public double nextDouble() {
            return randomValue;
        }
    }
}
