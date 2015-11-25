import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CommandLineTest implements UserInterface {
    private OutputStream output;
    private PrintStream printStream;
    private CommandLineUI cli;

    @Before
    public void setUp() {
        output = new ByteArrayOutputStream();
        printStream = new PrintStream(output);
    }

    @Test
    public void requestHumanTurn() {
        InputStream inputStream = new ByteArrayInputStream("1\n".getBytes());
        cli = new CommandLineUI(inputStream, printStream);
        assertEquals(Throw.ROCK, cli.requestConsoleTurn());
    }

    @Test
    public void humanTurnIsInvalid() {
        InputStream inputStream = new ByteArrayInputStream("99\n".getBytes());
        cli = new CommandLineUI(inputStream, printStream);
        assertEquals(false, cli.isValidThrow(Optional.empty()));
    }

    @Test
    public void invalidThenValidHumanChoice() {
        byte[] buf = "9\n2\n".getBytes();// 9 == invalid and 2 == Paper
        InputStream inputStream = new ByteArrayInputStream(buf);
        cli = new CommandLineUI(inputStream, printStream);
        assertEquals(Throw.PAPER, cli.requestConsoleTurn());
        assertThat(output.toString(), containsString(cli.INVALID_CHOICE));
    }

    @Test
    public void displayReplayChoices() {
        InputStream inputStream = new ByteArrayInputStream("2\n".getBytes());
        cli = new CommandLineUI(inputStream, printStream);
        assertEquals(true, cli.requestReplay());
        assertThat(output.toString(), containsString(cli.REPLAY_OPTION));
    }

    @Test
    public void invalidThenValidReplayChoice() {
        byte[] buf = "9\n2\n".getBytes();// 9 == invalid and 2 == Quit
        InputStream inputStream = new ByteArrayInputStream(buf);
        cli = new CommandLineUI(inputStream, printStream);
        assertEquals(true, cli.requestReplay());
        assertThat(output.toString(), containsString(cli.INVALID_CHOICE));
    }

    @Test
    public void requestCommandLineDisplaysGreeting() {
        InputStream inputStream = new ByteArrayInputStream("\n".getBytes());
        cli = new CommandLineUI(inputStream, printStream);
        cli.displayGreeting();
        assertThat(output.toString(), containsString(cli.GREETING_PROMPT));
    }
}
