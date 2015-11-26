import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConsolePlayerTest {
    @Test
    public void createComputerPlayerWithAThrow() {
        ConsolePlayer consolePlayer = new ConsolePlayer(Throw.ROCK);
        assertEquals(Throw.ROCK, consolePlayer.getThrow());
    }
}
