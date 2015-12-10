import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MarkTest {
    @Test
    public void verifyRockIdentifier() {
        Mark rock = Mark.ROCK;
        assertEquals(1, rock.getIdentifier());
    }

    @Test
    public void verifyPaperIdentifier() {
        Mark rock = Mark.PAPER;
        assertEquals(2, rock.getIdentifier());
    }

    @Test
    public void verifyScissorsIdentifier() {
        Mark rock = Mark.SCISSORS;
        assertEquals(3, rock.getIdentifier());
    }
}
