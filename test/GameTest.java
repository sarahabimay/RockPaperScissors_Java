import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GameTest {
    private OutputStream output;
    private PrintStream printStream;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
        printStream = new PrintStream(output);
    }

    @Test
    public void askCommandLineToDisplayGreeting() {
        InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        CommandLineUI cli = new CommandLineUI(inputStream, printStream);
        Game game = new Game(cli);
        game.askUIToDisplayGreeting();
        assertThat(output.toString(), containsString(cli.GREETING_PROMPT));
    }

    @Test
    public void askUIToGetAndDisplayConsoleUserPrompt() {
        InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        CommandLineUI cli = new CommandLineUI(inputStream, printStream);
        Game game = new Game(cli);
        game.obtainConsoleMoveAndDisplay();
        String expected = String.format(cli.CONSOLE_MOVE, Throw.ROCK);
        assertThat(output.toString(), containsString(expected));
    }

    @Test
    public void checkConsolePlayerWasCreated() {
        InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        CommandLineUI cli = new CommandLineUI(inputStream, printStream);
        Game game = new Game(cli);
        game.obtainConsoleMoveAndDisplay();
        String expected = String.format(cli.CONSOLE_MOVE, Throw.ROCK);
        assertEquals(1, game.getPlayers().size());
        assertThat(output.toString(), containsString(expected));
    }
}
